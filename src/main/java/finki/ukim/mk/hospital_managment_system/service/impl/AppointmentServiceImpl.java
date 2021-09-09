package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.*;
import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.repository.AppointmentRepository;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.repository.TermRepository;
import finki.ukim.mk.hospital_managment_system.service.AppointmentService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TermRepository termRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TermRepository termRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.termRepository = termRepository;
    }

    @Override
    public Appointment createAppointment(String status, Long patientId, Long doctorId, Long termId)
            throws PatientIdIsNull, DoctorIdIsNull, TermIdIsNull, ComponentException {

        if (Objects.isNull(patientId)) {
            throw new PatientIdIsNull("Patient ID can not be null!");
        }
        if (Objects.isNull(doctorId)) {
            throw new DoctorIdIsNull("Doctor ID can not be null!");
        }
        if (Objects.isNull(termId)) {
            throw new TermIdIsNull("Term ID can not be null!");
        }

        Appointment appointment = new Appointment();
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        Optional<Term> termOptional = termRepository.findById(termId);

        if (patientOptional.isEmpty() || doctorOptional.isEmpty() || termOptional.isEmpty()) {
            throw new ComponentException(String.format("Can not find patient with id: %d or doctor with id: %d or term with id: %d",
                    patientId, doctorId, termId));
        }

        Patient patient = patientOptional.get();
        Doctor doctor = doctorOptional.get();
        Term term = termOptional.get();

        // check if patient is doctor's patient
        Optional<Patient> p1 = doctor.getPatients()
                .stream()
                .filter(patient1 -> patient1.getId().equals(patient.getId())).findAny();
        if(p1.isEmpty()){
          doctor.follow(patient);
          patientRepository.save(patient);
          doctorRepository.save(doctor);
        }

        //set status to busy
        term.setStatus("busy");
        termRepository.save(term);
        LocalDate date = term.getDate();
        LocalTime time = term.getTimeOfAdmission();

        appointment.createAppointment(status, date, time, LocalDateTime.now(), patient, doctor);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public void deleteById(Long appointmentId) throws AppointmentIdIsNull {
        if (Objects.isNull(appointmentId)) {
            throw new AppointmentIdIsNull("Appointment ID can not be null!");
        }
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> saveAll(List<Appointment> appointments) {
        return appointmentRepository.saveAll(appointments);
    }

    @Override
    public List<Appointment> findAllByPatientId(Long patientId) throws PatientIdIsNull {
        if (Objects.isNull(patientId)) {
            throw new PatientIdIsNull("Patient ID can not be null!");
        }
        return appointmentRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Appointment> findAllByDoctorId(Long doctorId) throws DoctorIdIsNull {
        if (Objects.isNull(doctorId)) {
            throw new DoctorIdIsNull("Doctor ID can not be null!");
        }
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public Appointment findById(Long appointmentId) throws AppointmentIdIsNull {
        if (Objects.isNull(appointmentId)) {
            throw new AppointmentIdIsNull("Appointment ID can not be null!");
        }
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        return appointment.orElse(null);
    }

    @Override
    public Appointment updateStatus(Long appointmentId, String status) throws AppointmentIdIsNull {
        if (Objects.isNull(appointmentId)) {
            throw new AppointmentIdIsNull("Appointment ID can not be null!");
        }
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        Appointment appointment = new Appointment();
        if (appointmentOptional.isPresent()) {
            appointment = appointmentOptional.get();
            appointment.setStatus(status);
            appointmentRepository.save(appointment);
        }
        return appointment;
    }

    @Override
    public Integer numberOfAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.size();
    }
}

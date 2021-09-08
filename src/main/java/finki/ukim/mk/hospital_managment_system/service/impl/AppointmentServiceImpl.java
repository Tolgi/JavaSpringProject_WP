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
    public Appointment createAppointment(String status, Long patientId, Long doctorId, Long termId) {

        Appointment appointment = new Appointment();
        Patient patient = patientRepository.findById(patientId).orElseThrow(InvalidPatientId::new);
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(InvalidDoctorId::new);
        Optional<Patient> p1 = doctor.getPatients()
                .stream()
                .filter(patient1 -> patient1.getId().equals(patient.getId())).findAny();

        if(p1.isEmpty()){
          doctor.follow(patient);
          patientRepository.save(patient);
          doctorRepository.save(doctor);
        }

        Term term = termRepository.findById(termId).orElseThrow(InvalidTermId::new);
        term.setStatus("busy");
        termRepository.save(term);

        LocalDate date = term.getDate();
        LocalTime time = term.getTimeOfAdmission();

        appointment.createAppointment(status, date, time, LocalDateTime.now(), patient, doctor);
        appointmentRepository.save(appointment);

        return appointment;
    }

    @Override
    public void deleteById(Long appointmentId) {
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
    public List<Appointment> findAllByPatientId(Long patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Appointment> findAllByDoctorId(Long doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public Appointment findById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(InvalidAppointmentId::new);
    }

    @Override
    public Appointment updateStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(InvalidAppointmentId::new);
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Integer numberOfAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.size();
    }
}

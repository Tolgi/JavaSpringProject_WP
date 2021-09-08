package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.*;
import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.repository.SpecializationRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaUserRepository;
import finki.ukim.mk.hospital_managment_system.service.AppointmentService;
import finki.ukim.mk.hospital_managment_system.service.DoctorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final SpecializationRepository specializationRepository;
    private final AppointmentService appointmentService;
    private final JpaUserRepository userRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository, SpecializationRepository specializationRepository,
                             AppointmentService appointmentService, JpaUserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.specializationRepository = specializationRepository;
        this.appointmentService = appointmentService;
        this.userRepository = userRepository;
    }

    @Override
    public Doctor createDoctor(Long id, String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId) {

        Doctor doctor = new Doctor();
        Specialization specialization = this.specializationRepository.findById(specializationId).orElseThrow(InvalidSpecializationId::new);
        doctor.createDoctor(id, name, address, consultancyFees, contactNo, email, specialization);
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public void deleteById(Long id) {
        List<Appointment> appointments = appointmentService.findAllByDoctorId(id);
        appointments.forEach(appointment -> appointment.setDoctor(null));
        appointmentService.saveAll(appointments);
        doctorRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<Doctor> findAllBySpecializationName(String specializationName) throws SpecializationNameIsNullOrEmpty {
        if (Objects.isNull(specializationName) || specializationName.isEmpty()) {
            throw new SpecializationNameIsNullOrEmpty("Specialization name can not be null or empty!");
        }
        return doctorRepository.searchDoctorsBySpecialization(specializationName);
    }

    @Override
    public List<Patient> findPatientsByDoctorId(Long doctorId) throws DoctorIdIsNull, InvalidDoctor_ID {

        if (Objects.isNull(doctorId)) {
            throw new DoctorIdIsNull("Doctor id can not be null!");
        }
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (doctor.isEmpty()) {
            throw new InvalidDoctor_ID("Doctor ID is invalid!");
        }
        return doctor.get().getPatients();
    }

    @Override
    public List<Doctor> searchAllByName(String name) throws DoctorNameIsNullOrEmpty {

        if (Objects.isNull(name) || name.isEmpty()) {
            throw new DoctorNameIsNullOrEmpty("Doctor name is null or empty!");
        }
        return doctorRepository.searchDoctorsByName(name);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(Long doctorId) throws DoctorIdIsNull, InvalidDoctor_ID {

        if (Objects.isNull(doctorId)) {
            throw new DoctorIdIsNull("Doctor id can not be null!");
        }

        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (doctor.isEmpty()) {
            throw new InvalidDoctor_ID("Doctor ID is invalid!");
        }

        return doctor.get();
    }

    @Override
    public List<Doctor> findAllBySpecializationId(Long specializationId) throws SpecializationIdIsNull {

        if (Objects.isNull(specializationId)) {
            throw new SpecializationIdIsNull("Specialization ID is null!");
        }
        return doctorRepository.findAllBySpecializationId(specializationId);
    }

    @Override
    public Doctor editDoctor(Long doctorId, String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId) throws SpecializationIdIsNull, DoctorIdIsNull {

        if (Objects.isNull(doctorId)) {
            throw new DoctorIdIsNull("Doctor ID is null!");
        }

        if (Objects.isNull(specializationId)) {
            throw new SpecializationIdIsNull("Specialization ID is null!");
        }
        Optional<Doctor> optDoctor = doctorRepository.findById(doctorId);
        Optional<Specialization> specialization = specializationRepository.findById(specializationId);
        Doctor doctor = new Doctor();
        if (optDoctor.isPresent() && specialization.isPresent()) {
            doctor = optDoctor.get();
            doctor.setName(name);
            doctor.setAddress(address);
            doctor.setConsultancyFees(consultancyFees);
            doctor.setContactNo(contactNo);
            doctor.setEmail(email);
            doctor.setSpecialization(specialization.get());
            doctorRepository.save(doctor);
        }
        return doctor;
    }

    @Override
    public Integer numbersOfDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.size();
    }
}

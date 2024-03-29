package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.exceptions.InvalidSpecializationId;
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
    public List<Doctor> findAllBySpecializationName(String specializationName) {
        return doctorRepository.searchDoctorsBySpecialization(specializationName);
    }

    @Override
    public List<Patient> findPatientsByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(InvalidDoctorId::new);
        return doctor.getPatients();
    }

    @Override
    public List<Doctor> searchAllByName(String name) {
        return doctorRepository.searchDoctorsByName(name);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(InvalidDoctorId::new);
    }

    @Override
    public List<Doctor> findAllBySpecializationId(Long specializationId) {
        return doctorRepository.findAllBySpecializationId(specializationId);
    }

    @Override
    public Doctor editDoctor(Long doctorId, String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(InvalidDoctorId::new);
        Specialization specialization = specializationRepository.findById(specializationId).orElseThrow(InvalidSpecializationId::new);
        doctor.setName(name);
        doctor.setAddress(address);
        doctor.setConsultancyFees(consultancyFees);
        doctor.setContactNo(contactNo);
        doctor.setEmail(email);
        doctor.setSpecialization(specialization);
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Integer numbersOfDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        Integer number = doctors.size();
        return number;
    }
}

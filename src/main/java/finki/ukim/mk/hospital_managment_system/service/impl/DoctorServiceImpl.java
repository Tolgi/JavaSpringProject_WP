package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.exceptions.InvalidSpecializationId;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.repository.SpecializationRepository;
import finki.ukim.mk.hospital_managment_system.service.DoctorService;

import java.util.List;
import java.util.Optional;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final SpecializationRepository specializationRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository, SpecializationRepository specializationRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.specializationRepository = specializationRepository;
    }

    @Override
    public Doctor createDoctor(String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId) {

        Doctor doctor = new Doctor();
        Specialization specialization = this.specializationRepository.findById(specializationId).orElseThrow(InvalidSpecializationId::new);
        doctor.createDoctor(name, address, consultancyFees, contactNo, email, specialization);
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
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
    public Optional<Doctor> findById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }
}

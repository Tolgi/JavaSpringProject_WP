package finki.ukim.mk.hospital_managment_system.repository.impl;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaDoctorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

    private final JpaDoctorRepository jpaDoctorRepository;

    public DoctorRepositoryImpl(JpaDoctorRepository jpaDoctorRepository) {
        this.jpaDoctorRepository = jpaDoctorRepository;
    }

    @Override
    public Optional<Doctor> findById(Long doctorId) {
        return jpaDoctorRepository.findById(doctorId);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return jpaDoctorRepository.save(doctor);
    }

    @Override
    public void deleteById(Long doctorId) {
        jpaDoctorRepository.deleteById(doctorId);
    }

    @Override
    public void deleteAll(List<Doctor> doctors) {
        jpaDoctorRepository.deleteAll();
    }

    @Override
    public List<Doctor> findAll() {
        return jpaDoctorRepository.findAll();
    }

    @Override
    public List<Doctor> searchDoctorsBySpecialization(String term) {
        return jpaDoctorRepository.findDoctorsBySpecialization_Name(term);
    }

    @Override
    public List<Doctor> searchDoctorsByName(String term) {
        return jpaDoctorRepository.findDoctorsByNameContains(term);
    }

    @Override
    public List<Doctor> findAllBySpecializationId(Long specializationId) {
        return jpaDoctorRepository.findAllBySpecializationId(specializationId);
    }
}

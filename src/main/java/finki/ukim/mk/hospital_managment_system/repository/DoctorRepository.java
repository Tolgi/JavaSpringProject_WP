package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    Optional<Doctor> findById(String doctorId);

    Doctor save(Doctor doctor);

    void deleteById(String doctorId);

    List<Doctor> findAll();

    List<Doctor> searchDoctorsBySpecialization(String term);

    List<Doctor> searchDoctorsByName(String term);



}

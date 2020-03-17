package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    Optional<Doctor> findById(Long doctorId);

    Doctor save(Doctor doctor);

    void deleteById(Long doctorId);

    List<Doctor> findAll();

    List<Doctor> searchDoctorsBySpecialization(String term);

    List<Doctor> searchDoctorsByName(String term);

}

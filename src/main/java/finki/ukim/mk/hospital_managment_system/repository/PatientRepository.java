package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Patient;

import java.util.Optional;

public interface PatientRepository {

    Optional<Patient> findById(String patientId);

    Patient save(Patient patient);


    void deleteById(String patientId);

}

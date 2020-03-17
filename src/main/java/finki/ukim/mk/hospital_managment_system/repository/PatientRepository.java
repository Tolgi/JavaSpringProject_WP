package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Patient;

import java.util.Optional;

public interface PatientRepository {

    Optional<Patient> findById(Long patientId);

    Patient save(Patient patient);

    void deleteById(Long patientId);

}

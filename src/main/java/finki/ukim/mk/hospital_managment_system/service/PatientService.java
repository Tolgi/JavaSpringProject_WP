package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Patient;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientService {

    Patient createPatient(String name, Long ssn, String gender, String email, String address, Integer age, String contactNo, LocalDateTime creationDate, String [] doctorsList);

    void deleteById(Long patientId);

    List<Patient> findAll();

}

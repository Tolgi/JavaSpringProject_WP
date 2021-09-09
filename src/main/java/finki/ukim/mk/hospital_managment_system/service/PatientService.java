package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidPatient_ID;
import finki.ukim.mk.hospital_managment_system.exceptions.PatientIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import java.util.List;

public interface PatientService {

    Patient createPatient(Long id, String name, Long ssn, String gender, String email, String address, Integer age, String contactNo, Long doctorId);

    void deleteById(Long patientId);

    List<Patient> findAll();

    Patient getPatient(Long patientId) throws PatientIdIsNull, InvalidPatient_ID;

    Patient editPatient(Long patientId, String name, Long ssn, String gender, String email, String address, Integer age, String contactNo) throws PatientIdIsNull;

    Integer numbersOfPatients();
}

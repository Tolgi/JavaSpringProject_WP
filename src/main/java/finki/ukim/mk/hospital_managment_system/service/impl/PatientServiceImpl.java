package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.service.PatientService;

import java.time.LocalDateTime;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(String name, Long ssn, String gender, String email, String address, Integer age, String contactNo, LocalDateTime creationDate) {
        Patient patient = new Patient();
        patient.createPatient(name, ssn, gender, email, address, age, contactNo, creationDate);
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public void deleteById(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}

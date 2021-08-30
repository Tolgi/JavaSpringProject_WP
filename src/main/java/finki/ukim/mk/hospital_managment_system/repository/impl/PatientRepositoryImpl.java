package finki.ukim.mk.hospital_managment_system.repository.impl;

import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaPatientRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final JpaPatientRepository jpaPatientRepository;

    public PatientRepositoryImpl(JpaPatientRepository jpaPatientRepository) {
        this.jpaPatientRepository = jpaPatientRepository;
    }

    @Override
    public Optional<Patient> findById(Long patientId) {
        return jpaPatientRepository.findById(patientId);
    }

    @Override
    public Patient save(Patient patient) {
        return jpaPatientRepository.save(patient);
    }

    @Override
    public void deleteById(Long patientId) {
        jpaPatientRepository.deleteById(patientId);
    }

    @Override
    public List<Patient> findAll() {
        return jpaPatientRepository.findAll();
    }
}

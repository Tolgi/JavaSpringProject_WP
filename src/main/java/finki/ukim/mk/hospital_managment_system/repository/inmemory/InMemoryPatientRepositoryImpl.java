package finki.ukim.mk.hospital_managment_system.repository.inmemory;

import finki.ukim.mk.hospital_managment_system.StaticData.DataHolder;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryPatientRepositoryImpl implements PatientRepository {

    @Override
    public Optional<Patient> findById(String patientId) {
        return DataHolder.patients.stream()
                .filter(patient -> patient.getId().equals(patientId))
                .findFirst();
    }

    @Override
    public Patient save(Patient patient) {
        this.findById(patient.getId()).ifPresent(patient1 -> DataHolder.patients.remove(patient));
        DataHolder.patients.add(patient);
        return patient;
    }

    @Override
    public void deleteById(String patientId) {
        this.findById(patientId).ifPresent(patient -> DataHolder.patients.remove(patient));
    }
}

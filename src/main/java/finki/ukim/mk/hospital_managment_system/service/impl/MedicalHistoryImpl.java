package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidPatientId;
import finki.ukim.mk.hospital_managment_system.exceptions.MedicationHistoryIdIsNull;
import finki.ukim.mk.hospital_managment_system.exceptions.PatientIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.MedicalHistoryRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.service.MedicalHistoryService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MedicalHistoryImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientRepository patientRepository;

    public MedicalHistoryImpl(MedicalHistoryRepository medicalHistoryRepository, PatientRepository patientRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public MedicalHistory createMedicalHistory(String bloodPressure, Integer weight, Integer bloodSugar, String bodyTemperature, String medicalPerscription,  Long patientId) throws PatientIdIsNull {
        if (Objects.isNull(patientId)) {
            throw new PatientIdIsNull("Patient ID is null!");
        }

        MedicalHistory medicalHistory = new MedicalHistory();
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            medicalHistory.createMedicalHistory(bloodPressure, weight, bloodSugar, bodyTemperature, medicalPerscription, LocalDateTime.now(), patient.get());
            medicalHistoryRepository.save(medicalHistory);
        }

        return medicalHistory;
    }

    @Override
    public void deleteById(Long medicalHistoryId) throws MedicationHistoryIdIsNull {
        if (Objects.isNull(medicalHistoryId)) {
            throw new MedicationHistoryIdIsNull("Medical history ID is null!");
        }
        medicalHistoryRepository.deleteById(medicalHistoryId);
    }

    @Override
    public List<MedicalHistory> findAllByPatientId(Long patientId) throws PatientIdIsNull {
        if (Objects.isNull(patientId)) {
            throw new PatientIdIsNull("Patient ID is null!");
        }
        return medicalHistoryRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<MedicalHistory> saveAll(List<MedicalHistory> medicalHistories) {
        return medicalHistoryRepository.saveAll(medicalHistories);
    }
}

package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidPatientId;
import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.MedicalHistoryRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.service.MedicalHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalHistoryImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientRepository patientRepository;

    public MedicalHistoryImpl(MedicalHistoryRepository medicalHistoryRepository, PatientRepository patientRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public MedicalHistory createMedicalHistory(String bloodPressure, Integer weight, Integer bloodSugar, String bodyTemperature, String medicalPerscription, LocalDateTime visitDate, Long patientId) {
        MedicalHistory medicalHistory = new MedicalHistory();
        Patient patient = patientRepository.findById(patientId).orElseThrow(InvalidPatientId::new);
        medicalHistory.createMedicalHistory(bloodPressure, weight, bloodSugar, bodyTemperature, medicalPerscription, visitDate, patient);
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }

    @Override
    public void deleteById(Long medicalHistoryId) {
        medicalHistoryRepository.deleteById(medicalHistoryId);
    }

    @Override
    public List<MedicalHistory> findAllByPatientId(Long patientId) {
        return medicalHistoryRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<MedicalHistory> saveAll(List<MedicalHistory> medicalHistories) {
        return medicalHistoryRepository.saveAll(medicalHistories);
    }
}

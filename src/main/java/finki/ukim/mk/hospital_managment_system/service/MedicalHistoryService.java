package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalHistoryService {

    MedicalHistory createMedicalHistory(String bloodPressure, Integer weight, Integer bloodSugar, String bodyTemperature, String medicalPerscription, LocalDateTime visitDate, Long patientId);

    void deleteById(Long medicalHistoryId);

    List<MedicalHistory> findAllByPatientId(Long patientId);


}

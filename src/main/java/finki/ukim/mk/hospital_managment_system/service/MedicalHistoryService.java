package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.exceptions.MedicationHistoryIdIsNull;
import finki.ukim.mk.hospital_managment_system.exceptions.PatientIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import java.util.List;

public interface MedicalHistoryService {

    MedicalHistory createMedicalHistory(String bloodPressure, Integer weight, Integer bloodSugar, String bodyTemperature, String medicalPerscription, Long patientId) throws PatientIdIsNull;

    void deleteById(Long medicalHistoryId) throws MedicationHistoryIdIsNull;

    List<MedicalHistory> findAllByPatientId(Long patientId) throws PatientIdIsNull;

    List<MedicalHistory> saveAll(List<MedicalHistory> medicalHistories);
}

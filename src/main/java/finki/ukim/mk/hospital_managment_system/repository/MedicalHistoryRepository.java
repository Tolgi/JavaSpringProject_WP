package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;

public interface MedicalHistoryRepository {

    MedicalHistory save(MedicalHistory medicalHistory);

    void deleteById(String medicalHistoryId);
}

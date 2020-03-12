package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;

import java.util.Optional;

public interface MedicalHistoryRepository {

    MedicalHistory save(MedicalHistory medicalHistory);

    Optional<MedicalHistory> findById(String medicalHistoryId);

    void deleteById(String medicalHistoryId);
}

package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import java.util.List;
import java.util.Optional;

public interface MedicalHistoryRepository {

    MedicalHistory save(MedicalHistory medicalHistory);

    Optional<MedicalHistory> findById(Long medicalHistoryId);

    void deleteById(Long medicalHistoryId);

    List<MedicalHistory> findAllByPatientId(Long patientId);

    List<MedicalHistory> saveAll(List<MedicalHistory> medicalHistories);
}

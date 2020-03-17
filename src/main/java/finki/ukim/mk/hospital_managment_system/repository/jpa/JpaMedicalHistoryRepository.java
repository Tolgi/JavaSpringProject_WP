package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

    List<MedicalHistory> findMedicalHistoriesByPatient_Id(Long patientId);
}

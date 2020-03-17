package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
}

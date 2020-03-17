package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSpecializationRepository extends JpaRepository<Specialization, Long> {
}

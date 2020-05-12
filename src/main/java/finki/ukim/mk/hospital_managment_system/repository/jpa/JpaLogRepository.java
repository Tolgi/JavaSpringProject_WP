package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaLogRepository extends JpaRepository<Log, Long> {
    Optional<Log> findById(Long logId);

    Log save(Log log);

    List<Log> findAll();
}
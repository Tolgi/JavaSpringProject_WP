package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTermRepository extends JpaRepository<Term, Long> {

    List<Term> findTermsByDoctor_IdAndAndStatus(Long doctorId, String status);
}

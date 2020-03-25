package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaTermRepository extends JpaRepository<Term, Long> {

    List<Term> findTermsByDoctor_IdAndAndStatus(Long doctorId, String status);
    List<Term> findTermsByStatusLike(String status);

    @Query("select t from Term t " +
            "WHERE t.doctor.id = :doctorId")
    List<Term> findTermsByDoctor(@Param("doctorId") Long doctorId);

}

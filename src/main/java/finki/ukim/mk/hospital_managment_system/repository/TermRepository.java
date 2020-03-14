package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Term;

import java.util.List;
import java.util.Optional;

public interface TermRepository {

    Optional<Term> findById(String termId);

    Term save(Term term);

    void deleteById(String termId);

    List<Term> searchByDoctorIdAndStatus(String doctorId, String status);

}

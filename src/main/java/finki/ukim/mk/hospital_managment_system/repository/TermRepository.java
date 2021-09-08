package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.exceptions.DoctorIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Term;
import java.util.List;
import java.util.Optional;

public interface TermRepository {

    Optional<Term> findById(Long termId);

    Term save(Term term);

    void deleteById(Long termId);

    List<Term> searchByDoctorIdAndStatus(Long doctorId, String status);

    List<Term> searchByStatus(String status);

    List<Term> searchByDoctorId(Long doctorId) throws DoctorIdIsNull;

    List<Term> findAll();
}

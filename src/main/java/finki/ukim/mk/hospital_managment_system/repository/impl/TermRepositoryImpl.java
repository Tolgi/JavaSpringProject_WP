package finki.ukim.mk.hospital_managment_system.repository.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.DoctorIdIsNull;
import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.repository.TermRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaTermRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TermRepositoryImpl implements TermRepository {

    private final JpaTermRepository jpaTermRepository;

    public TermRepositoryImpl(JpaTermRepository jpaTermRepository) {
        this.jpaTermRepository = jpaTermRepository;
    }

    @Override
    public Optional<Term> findById(Long termId) {
        return jpaTermRepository.findById(termId);
    }

    @Override
    public Term save(Term term) {
        return jpaTermRepository.save(term);
    }

    @Override
    public void deleteById(Long termId) {
        jpaTermRepository.deleteById(termId);
    }

    @Override
    public List<Term> searchByDoctorIdAndStatus(Long doctorId, String status) {
        return jpaTermRepository.findTermsByDoctor_IdAndAndStatusOrderByDateDesc(doctorId, status);
    }

    @Override
    public List<Term> searchByStatus(String status) {
        return jpaTermRepository.findTermsByStatusLike(status);
    }

    @Override
    public List<Term> searchByDoctorId(Long doctorId) {
        return jpaTermRepository.findTermsByDoctor(doctorId);
    }

    @Override
    public List<Term> findAll() {
        return jpaTermRepository.findAll();
    }
}

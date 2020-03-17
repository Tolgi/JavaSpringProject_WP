package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.repository.TermRepository;

import java.util.List;
import java.util.Optional;

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
        return jpaTermRepository.findTermsByDoctor_IdAndAndStatus(doctorId, status);
    }

    @Override
    public List<Term> searchByStatus(String status) {
        return jpaTermRepository.findTermsByStatus(status);
    }

    @Override
    public List<Term> searchByDoctorId(Long doctorId) {
        return jpaTermRepository.findTermsByDoctor_Id(doctorId);
    }

    @Override
    public List<Term> findAll() {
        return jpaTermRepository.findAll();
    }
}

package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.TermRepository;
import finki.ukim.mk.hospital_managment_system.service.TermService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TermServiceImpl implements TermService {

    private final TermRepository termRepository;
    private final DoctorRepository doctorRepository;

    public TermServiceImpl(TermRepository termRepository, DoctorRepository doctorRepository) {
        this.termRepository = termRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Term createTerm(LocalDate date, LocalTime time, String status, Long doctorId) {
        Term term = new Term();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(InvalidDoctorId::new);
        term.createTerm(date, time, status, doctor);
        termRepository.save(term);
        return term;
    }

    @Override
    public void deleteById(Long termId) {
        termRepository.deleteById(termId);
    }

    @Override
    public List<Term> findAll() {
        return termRepository.findAll();
    }

    @Override
    public List<Term> findAllByDoctorId(Long doctorId) {
        return termRepository.searchByDoctorId(doctorId);
    }

    @Override
    public List<Term> findAllByStatus(String status) {
        return termRepository.searchByStatus(status);
    }

    @Override
    public List<Term> findAllByDoctorIdAndByStatus(Long doctorId, String status) {
        return termRepository.searchByDoctorIdAndStatus(doctorId, status);
    }
}

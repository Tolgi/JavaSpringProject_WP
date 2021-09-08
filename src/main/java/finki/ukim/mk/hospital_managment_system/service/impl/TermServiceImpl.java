package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.*;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.TermRepository;
import finki.ukim.mk.hospital_managment_system.service.TermService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TermServiceImpl implements TermService {

    private final TermRepository termRepository;
    private final DoctorRepository doctorRepository;

    public TermServiceImpl(TermRepository termRepository, DoctorRepository doctorRepository) {
        this.termRepository = termRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Term createTerm(LocalDate date, LocalTime time, String status, Long doctorId) throws ScheduledTerm, DoctorIdIsNull {
        Optional<Term>termOptional = findAllByDoctorId(doctorId)
                .stream()
                .filter(term -> term.getDate().equals(date) &&
                        term.getTimeOfAdmission().equals(time))
                .findAny();

        if(termOptional.isPresent()){
            throw new ScheduledTerm("This term is already taken!");
        }

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
        List<Term> allTerms = termRepository.findAll();
        return allTerms.stream()
                .filter(term -> term.getDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Term::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Term> findAllByDoctorId(Long doctorId) throws DoctorIdIsNull {
        if (Objects.isNull(doctorId)) {
            throw new DoctorIdIsNull("Doctor id can not be null or empty!");
        }

        List<Term> allTerms = termRepository.searchByDoctorId(doctorId);
        return allTerms.stream()
                .filter(term -> term.getDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Term::getDate).thenComparing(Term::getTimeOfAdmission).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Term> findAllByStatus(String status) throws InvalidTermStatus {
        if (status.equals("free") || status.equals("busy")) {
            return termRepository.searchByStatus(status);
        } else {
            throw new InvalidTermStatus("Term status is invalid!");
        }
    }

    @Override
    public List<Term> findAllByDoctorIdAndByStatus(Long doctorId, String status) throws DoctorIdIsNull, InvalidTermStatus {

        if (Objects.isNull(doctorId)) {
            throw new DoctorIdIsNull("Doctor id can not be null or empty!");
        }

        if (!status.equals("free") && !status.equals("busy")) {
            throw new InvalidTermStatus("Term status is invalid!");
        }

        List<Term> allTerms = termRepository.searchByDoctorIdAndStatus(doctorId, status);
        return allTerms.stream()
                .filter(term -> term.getDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
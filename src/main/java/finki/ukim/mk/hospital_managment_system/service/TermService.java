package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Term;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TermService {

    Term createTerm(LocalDate date, LocalTime time, String status, String doctorId);

    void deleteById(Long termId);

    List<Term> findAll();

    List<Term> findAllByDoctorId(Long doctorId);

    List<Term> findAllByStatus(String status);

    List<Term> findAllByDoctorIdAndByStatus(Long doctorId, String status);
}

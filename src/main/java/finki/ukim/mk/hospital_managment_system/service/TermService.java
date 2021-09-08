package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.exceptions.DoctorIdIsNull;
import finki.ukim.mk.hospital_managment_system.exceptions.InvalidTermStatus;
import finki.ukim.mk.hospital_managment_system.exceptions.ScheduledTerm;
import finki.ukim.mk.hospital_managment_system.model.Term;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TermService {

    Term createTerm(LocalDate date, LocalTime time, String status, Long doctorId) throws ScheduledTerm, DoctorIdIsNull;

    void deleteById(Long termId);

    List<Term> findAll();

    List<Term> findAllByDoctorId(Long doctorId) throws DoctorIdIsNull;

    List<Term> findAllByStatus(String status) throws InvalidTermStatus;

    List<Term> findAllByDoctorIdAndByStatus(Long doctorId, String status) throws DoctorIdIsNull, InvalidTermStatus;
}

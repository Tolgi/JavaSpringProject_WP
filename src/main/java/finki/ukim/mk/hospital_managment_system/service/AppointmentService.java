package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Integer consultancyFees, String status, LocalDateTime creationDate, Long patientId, Long doctorId, Long termId);

    void deleteById(Long appointmentId);

    List<Appointment> findAll();

    List<Appointment> saveAll(List<Appointment> appointments);

    List<Appointment> findAllByPatientId(Long patientId);

    List<Appointment> findAllByDoctorId(Long doctorId);

    Appointment findById(Long appointmentId);
}

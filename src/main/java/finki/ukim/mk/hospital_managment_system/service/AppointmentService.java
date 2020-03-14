package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    AppointmentService createAppointment(Integer consultancyFees, String status, LocalDate date, LocalTime time, LocalDateTime creationDate, String patientId, String doctorId);

    void deleteById(String appointmentId);

    List<Appointment> findAll();

    List<Appointment> findAllByPatientId(String patientId);

    List<Appointment> findAllByDoctorId(String doctorId);
}

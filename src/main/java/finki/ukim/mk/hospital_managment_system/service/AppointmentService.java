package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.exceptions.*;
import finki.ukim.mk.hospital_managment_system.model.Appointment;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(String status, Long patientId, Long doctorId, Long termId) throws PatientIdIsNull, DoctorIdIsNull, TermIdIsNull, ComponentException;

    void deleteById(Long appointmentId) throws AppointmentIdIsNull;

    List<Appointment> findAll();

    List<Appointment> saveAll(List<Appointment> appointments);

    List<Appointment> findAllByPatientId(Long patientId) throws PatientIdIsNull;

    List<Appointment> findAllByDoctorId(Long doctorId) throws DoctorIdIsNull;

    Appointment findById(Long appointmentId) throws AppointmentIdIsNull;

    Appointment updateStatus(Long appointmentId, String status) throws AppointmentIdIsNull;

    Integer numberOfAppointments();
}

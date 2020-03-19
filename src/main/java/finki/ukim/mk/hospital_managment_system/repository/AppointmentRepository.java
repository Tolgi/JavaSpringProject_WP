package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Appointment;


import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    Optional<Appointment> findById(Long appointmentId);

    Appointment save(Appointment appointment);

    void deleteById(Long appointmentId);

    List<Appointment> findAllByPatientId(Long patientId);

    List<Appointment> findAllByDoctorId(Long doctorId);

    List<Appointment> findAll();
}

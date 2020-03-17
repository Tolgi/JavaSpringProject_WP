package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Appointment;
import sun.security.krb5.internal.APOptions;

import java.util.Optional;

public interface AppointmentRepository {

    Optional<Appointment> findById(Long appointmentId);

    Appointment save(Appointment appointment);

    void deleteById(Long appointmentId);
}

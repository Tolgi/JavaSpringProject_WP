package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentsByPatient_Id(Long patientId);

    List<Appointment> findAppointmentsByDoctor_Id(Long doctorId);
}

package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.repository.AppointmentRepository;

import java.util.Optional;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final JpaAppointmentRepository jpaAppointmentRepository;

    public AppointmentRepositoryImpl(JpaAppointmentRepository jpaAppointmentRepository) {
        this.jpaAppointmentRepository = jpaAppointmentRepository;
    }


    @Override
    public Optional<Appointment> findById(Long appointmentId) {
        return jpaAppointmentRepository.findById(appointmentId);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return jpaAppointmentRepository.save(appointment);
    }

    @Override
    public void deleteById(Long appointmentId) {
        jpaAppointmentRepository.deleteById(appointmentId);
    }
}

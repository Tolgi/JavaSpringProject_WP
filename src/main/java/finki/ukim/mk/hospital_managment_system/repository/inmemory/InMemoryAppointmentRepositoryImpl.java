package finki.ukim.mk.hospital_managment_system.repository.inmemory;

import finki.ukim.mk.hospital_managment_system.StaticData.DataHolder;
import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.repository.AppointmentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryAppointmentRepositoryImpl implements AppointmentRepository {

    @Override
    public Optional<Appointment> findById(String appointmentId) {
        return DataHolder.appointments.stream()
                .filter(appointment -> appointment.getId().equals(appointmentId))
                .findFirst();
    }

    @Override
    public Appointment save(Appointment appointment) {
        this.findById(appointment.getId()).ifPresent(appointment1 -> DataHolder.appointments.remove(appointment1));
        DataHolder.appointments.add(appointment);
        return appointment;
    }

    @Override
    public void deleteById(String appointmentId) {
        this.findById(appointmentId).ifPresent(appointment -> DataHolder.appointments.remove(appointment));
    }
}

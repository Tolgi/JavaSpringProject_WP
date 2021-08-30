package finki.ukim.mk.hospital_managment_system.repository.impl;

import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.repository.AppointmentRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaAppointmentRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
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
    public List<Appointment> saveAll(List<Appointment> appointments) {
        return jpaAppointmentRepository.saveAll(appointments);
    }

    @Override
    public void deleteById(Long appointmentId) {
        jpaAppointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<Appointment> findAllByPatientId(Long patientId) {
        return jpaAppointmentRepository.findAppointmentsByPatient_Id(patientId);
    }

    @Override
    public List<Appointment> findAllByDoctorId(Long doctorId) {
        return jpaAppointmentRepository.findAppointmentsByDoctor_Id(doctorId);
    }

    @Override
    public List<Appointment> findAll() {
        return jpaAppointmentRepository.findAll();
    }
}

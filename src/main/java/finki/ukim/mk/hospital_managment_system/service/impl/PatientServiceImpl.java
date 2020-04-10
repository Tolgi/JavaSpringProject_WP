package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.service.AppointmentService;
import finki.ukim.mk.hospital_managment_system.service.MedicalHistoryService;
import finki.ukim.mk.hospital_managment_system.service.PatientService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentService appointmentService;
    private final MedicalHistoryService medicalHistoryService;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentService appointmentService, MedicalHistoryService medicalHistoryService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
        this.medicalHistoryService = medicalHistoryService;
    }

    @Override
    public Patient createPatient(String name, Long ssn, String gender, String email, String address, Integer age, String contactNo, LocalDateTime creationDate, Long doctorId) {
        Patient patient = new Patient();
        patient.createPatient(name, ssn, gender, email, address, age, contactNo, creationDate);
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(InvalidDoctorId::new);
        doctor.follow(patient);
        patientRepository.save(patient);
        doctorRepository.save(doctor);
        return patient;
    }

    @Override
    public void deleteById(Long patientId) {
        List<Appointment> appointments = appointmentService.findAllByPatientId(patientId);
        appointments.forEach(appointment -> appointment.setPatient(null));
        appointmentService.saveAll(appointments);

        List<MedicalHistory> medicalHistories = medicalHistoryService.findAllByPatientId(patientId);
        medicalHistories.forEach(medicalHistory -> medicalHistory.setPatient(null));
        medicalHistoryService.saveAll(medicalHistories);

        patientRepository.deleteById(patientId);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}

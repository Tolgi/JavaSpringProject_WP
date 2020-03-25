package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.service.PatientService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
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
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}

package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.exceptions.InvalidPatientId;
import finki.ukim.mk.hospital_managment_system.exceptions.InvalidPatient_ID;
import finki.ukim.mk.hospital_managment_system.exceptions.PatientIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaUserRepository;
import finki.ukim.mk.hospital_managment_system.service.AppointmentService;
import finki.ukim.mk.hospital_managment_system.service.MedicalHistoryService;
import finki.ukim.mk.hospital_managment_system.service.PatientService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentService appointmentService;
    private final MedicalHistoryService medicalHistoryService;
    private final JpaUserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentService appointmentService,
                              MedicalHistoryService medicalHistoryService, JpaUserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
        this.medicalHistoryService = medicalHistoryService;
        this.userRepository = userRepository;
    }

    @Override
    public Patient createPatient(Long id, String name, Long ssn, String gender, String email, String address, Integer age, String contactNo,  Long doctorId) {
        Patient patient = new Patient();
        patient.createPatient(id, name, ssn, gender, email, address, age, contactNo, LocalDateTime.now());
        patientRepository.save(patient);

        if(doctorId != null) {
            Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(InvalidDoctorId::new);
            doctor.follow(patient);
            doctorRepository.save(doctor);
        }
        patientRepository.save(patient);
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
        userRepository.deleteById(patientId);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatient(Long patientId) throws PatientIdIsNull, InvalidPatient_ID {
        if (Objects.isNull(patientId)) {
            throw new PatientIdIsNull("Patient ID is null!");
        }
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (!optionalPatient.isPresent()) {
            throw new InvalidPatient_ID(String.format("Can not find patient with id: %s", patientId));
        }

        return optionalPatient.get();
    }

    @Override
    public Patient editPatient(Long patientId, String name, Long ssn, String gender, String email, String address, Integer age, String contactNo) throws PatientIdIsNull {
        if (Objects.isNull(patientId)) {
            throw new PatientIdIsNull("Patient ID is null!");
        }
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        Patient patient = new Patient();
        if (optionalPatient.isPresent()) {
            patient = optionalPatient.get();
            patient.setName(name);
            patient.setSsn(ssn);
            patient.setAddress(address);
            patient.setAge(age);
            patient.setEmail(email);
            patient.setGender(gender);
            patient.setContactNo(contactNo);
            patientRepository.save(patient);
        }
        return patient;
    }

    @Override
    public Integer numbersOfPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.size();
    }
}

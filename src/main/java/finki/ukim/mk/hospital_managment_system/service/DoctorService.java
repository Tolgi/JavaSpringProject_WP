package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(String name, String address, Integer consultancyFees, String contactNo, String email, String specializationId, String [] patientsList);

    void deleteById(Long id);

    List<Doctor> findAllBySpecializationId(Long specializationId);

    List<Patient> findAllPatients();

    List<Doctor> findAll();
}

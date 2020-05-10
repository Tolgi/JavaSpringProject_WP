package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.model.Specialization;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    Doctor createDoctor(Long id, String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId);

    void deleteById(Long id);

    List<Doctor> findAllBySpecializationName(String specializationName);

    List<Patient> findPatientsByDoctorId(Long doctorId);

    List<Doctor> searchAllByName(String name);

    List<Doctor> findAll();

    Doctor findById(Long doctorId);

    List<Doctor> findAllBySpecializationId(Long specializationId);

    Doctor editDoctor(Long doctorId, String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId);

    Integer numbersOfDoctors();
}

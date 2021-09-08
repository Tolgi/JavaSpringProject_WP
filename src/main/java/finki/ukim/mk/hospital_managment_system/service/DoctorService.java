package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.exceptions.*;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Long id, String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId);

    void deleteById(Long id);

    List<Doctor> findAllBySpecializationName(String specializationName) throws SpecializationNameIsNullOrEmpty;

    List<Patient> findPatientsByDoctorId(Long doctorId) throws DoctorIdIsNull, InvalidDoctor_ID;

    List<Doctor> searchAllByName(String name) throws DoctorNameIsNullOrEmpty;

    List<Doctor> findAll();

    Doctor findById(Long doctorId) throws DoctorIdIsNull, InvalidDoctor_ID;

    List<Doctor> findAllBySpecializationId(Long specializationId) throws SpecializationIdIsNull;

    Doctor editDoctor(Long doctorId, String name, String address, Integer consultancyFees, String contactNo, String email, Long specializationId) throws SpecializationIdIsNull, DoctorIdIsNull;

    Integer numbersOfDoctors();
}

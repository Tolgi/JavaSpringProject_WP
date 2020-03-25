package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findDoctorsBySpecialization_Name(String specialization);
    List<Doctor> findDoctorsByNameContains(String name);

}

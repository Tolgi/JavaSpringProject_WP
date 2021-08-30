package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaDoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findDoctorsBySpecialization_Name(String specialization);
    List<Doctor> findAllBySpecializationId(Long specializationId);
    List<Doctor> findDoctorsByNameContains(String name);
}

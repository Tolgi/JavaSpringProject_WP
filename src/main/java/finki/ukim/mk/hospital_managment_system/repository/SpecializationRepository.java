package finki.ukim.mk.hospital_managment_system.repository;

import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.model.Specialization;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepository {

    Optional<Specialization> findById(Long specializationId);

    Specialization save(Specialization specialization);

    List<Specialization> findAll();

    void deleteById(Long id);

}

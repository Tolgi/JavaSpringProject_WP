package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Specialization;

import java.time.LocalDateTime;
import java.util.List;

public interface SpecializationService {

    Specialization createSpecialization(String name, LocalDateTime creationDate);

    void deleteById(Long specializationId);

    List<Specialization> findAll();

    Specialization editSpecialization(Long id, String name);

    Specialization findById(Long id);
}

package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.exceptions.SpecializationIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import java.util.List;

public interface SpecializationService {

    Specialization createSpecialization(String name);

    void deleteById(Long specializationId) throws SpecializationIdIsNull;

    List<Specialization> findAll();

    Specialization editSpecialization(Long id, String name);

    Specialization findById(Long id);
}

package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.repository.SpecializationRepository;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;

import java.time.LocalDateTime;
import java.util.List;

public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public Specialization createSpecialization(String name, LocalDateTime creationDate) {
        Specialization specialization = new Specialization();
        specialization.createSpecialization(name, creationDate);
        specializationRepository.save(specialization);
        return specialization;
    }

    @Override
    public void deleteById(Long specializationId) {
        specializationRepository.deleteById(specializationId);
    }

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }
}

package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.repository.SpecializationRepository;

import java.util.List;
import java.util.Optional;

public class SpecializationRepositoryImpl implements SpecializationRepository {

    private final JpaSpecializationRepository jpaSpecializationRepository;

    public SpecializationRepositoryImpl(JpaSpecializationRepository jpaSpecializationRepository) {
        this.jpaSpecializationRepository = jpaSpecializationRepository;
    }

    @Override
    public Optional<Specialization> findById(Long specializationId) {
        return jpaSpecializationRepository.findById(specializationId);
    }

    @Override
    public Specialization save(Specialization specialization) {
        return jpaSpecializationRepository.save(specialization);
    }

    @Override
    public List<Specialization> findAll() {
        return jpaSpecializationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaSpecializationRepository.deleteById(id);
    }
}

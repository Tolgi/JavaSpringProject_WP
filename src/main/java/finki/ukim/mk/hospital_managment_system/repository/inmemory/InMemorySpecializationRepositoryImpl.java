package finki.ukim.mk.hospital_managment_system.repository.inmemory;

import finki.ukim.mk.hospital_managment_system.StaticData.DataHolder;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.repository.SpecializationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySpecializationRepositoryImpl implements SpecializationRepository {

    @Override
    public Optional<Specialization> findById(Long specializationId) {
        return DataHolder.specializations.stream()
                .filter(specialization -> specialization.getId().equals(specializationId))
                .findFirst();
    }

    @Override
    public Specialization save(Specialization specialization) {
        this.findById(specialization.getId()).ifPresent(specialization1 -> DataHolder.specializations.remove(specialization1));
        DataHolder.specializations.add(specialization);
        return specialization;
    }

    @Override
    public List<Specialization> findAll() {
        return DataHolder.specializations;
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id).ifPresent(specialization -> DataHolder.specializations.remove(specialization));
    }
}

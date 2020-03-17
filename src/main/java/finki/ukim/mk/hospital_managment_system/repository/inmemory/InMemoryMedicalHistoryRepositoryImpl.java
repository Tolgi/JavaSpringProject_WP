package finki.ukim.mk.hospital_managment_system.repository.inmemory;

import finki.ukim.mk.hospital_managment_system.StaticData.DataHolder;
import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryMedicalHistoryRepositoryImpl implements MedicalHistoryRepository {

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) {
        this.findById(medicalHistory.getId()).ifPresent(medicalHistory1 -> DataHolder.medicalHistories.remove(medicalHistory1));
        DataHolder.medicalHistories.add(medicalHistory);
        return medicalHistory;
    }

    @Override
    public Optional<MedicalHistory> findById(Long medicalHistoryId) {
        return DataHolder.medicalHistories.stream()
                .filter(medicalHistory -> medicalHistory.getId().equals(medicalHistoryId))
                .findFirst();
    }

    @Override
    public void deleteById(Long medicalHistoryId) {
        this.findById(medicalHistoryId).ifPresent(medicalHistory -> DataHolder.medicalHistories.remove(medicalHistory));
    }
}

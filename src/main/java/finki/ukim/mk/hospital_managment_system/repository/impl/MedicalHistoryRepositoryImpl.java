package finki.ukim.mk.hospital_managment_system.repository.impl;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.repository.MedicalHistoryRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaMedicalHistoryRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicalHistoryRepositoryImpl implements MedicalHistoryRepository {

    private final JpaMedicalHistoryRepository jpaMedicalHistoryRepository;

    public MedicalHistoryRepositoryImpl(JpaMedicalHistoryRepository jpaMedicalHistoryRepository) {
        this.jpaMedicalHistoryRepository = jpaMedicalHistoryRepository;
    }

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) {
        return jpaMedicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public Optional<MedicalHistory> findById(Long medicalHistoryId) {
        return jpaMedicalHistoryRepository.findById(medicalHistoryId);
    }

    @Override
    public void deleteById(Long medicalHistoryId) {
        jpaMedicalHistoryRepository.deleteById(medicalHistoryId);
    }

    @Override
    public List<MedicalHistory> findAllByPatientId(Long patientId) {
        return jpaMedicalHistoryRepository.findMedicalHistoriesByPatient_Id(patientId);
    }

    @Override
    public List<MedicalHistory> saveAll(List<MedicalHistory> medicalHistories) {
        return jpaMedicalHistoryRepository.saveAll(medicalHistories);
    }
}

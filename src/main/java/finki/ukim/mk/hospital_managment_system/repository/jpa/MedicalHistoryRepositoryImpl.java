package finki.ukim.mk.hospital_managment_system.repository.jpa;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.repository.MedicalHistoryRepository;

import java.util.List;
import java.util.Optional;

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
}

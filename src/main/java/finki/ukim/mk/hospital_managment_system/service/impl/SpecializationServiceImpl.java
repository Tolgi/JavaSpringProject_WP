package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidSpecializationId;
import finki.ukim.mk.hospital_managment_system.exceptions.SpecializationIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.repository.SpecializationRepository;
import finki.ukim.mk.hospital_managment_system.service.DoctorService;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final DoctorService doctorService;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository, DoctorService doctorService) {
        this.specializationRepository = specializationRepository;
        this.doctorService = doctorService;
    }

    @Override
    public Specialization createSpecialization(String name) {
        Specialization specialization = new Specialization();
        specialization.createSpecialization(name, LocalDateTime.now());
        specializationRepository.save(specialization);
        return specialization;
    }

    @Override
    public void deleteById(Long specializationId) throws SpecializationIdIsNull {

        if (Objects.isNull(specializationId)) {
            throw new SpecializationIdIsNull("Specialization ID is NULL!");
        }
        List<Doctor> doctors = doctorService.findAllBySpecializationId(specializationId);
        doctors.forEach(doctor -> doctorService.deleteById(doctor.getId()));
        specializationRepository.deleteById(specializationId);
    }

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization editSpecialization(Long id, String name) {
        Specialization specialization = specializationRepository.findById(id).orElseThrow(InvalidSpecializationId::new);
        specialization.setName(name);
        specializationRepository.save(specialization);
        return specialization;
    }

    @Override
    public Specialization findById(Long id) {
        return specializationRepository.findById(id).orElseThrow(InvalidSpecializationId::new);
    }
}

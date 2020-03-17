package finki.ukim.mk.hospital_managment_system.repository.inmemory;

import finki.ukim.mk.hospital_managment_system.StaticData.DataHolder;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryDoctorRepositoryImpl implements DoctorRepository {

    @Override
    public Optional<Doctor> findById(Long doctorId) {
        return DataHolder.doctors.stream()
                .filter(doctor -> doctor.getId().equals(doctorId))
                .findFirst();
    }

    @Override
    public Doctor save(Doctor doctor) {
        this.findById(doctor.getId()).ifPresent(doctor1 -> DataHolder.doctors.remove(doctor1));
        DataHolder.doctors.add(doctor);
        return doctor;
    }

    @Override
    public void deleteById(Long doctorId) {
        this.findById(doctorId).ifPresent(doctor -> DataHolder.doctors.remove(doctor));
    }

    @Override
    public List<Doctor> findAll() {
        return DataHolder.doctors;
    }

    @Override
    public List<Doctor> searchDoctorsBySpecialization(String term) {
        return DataHolder.doctors.stream()
                .filter(doctor -> doctor.getSpecialization().getName().equals(term))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> searchDoctorsByName(String term) {
        return DataHolder.doctors.stream()
                .filter(doctor -> doctor.getName().equals(term))
                .collect(Collectors.toList());
    }


}

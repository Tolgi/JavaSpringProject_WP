package finki.ukim.mk.hospital_managment_system.repository.inmemory;

import finki.ukim.mk.hospital_managment_system.StaticData.DataHolder;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryDoctorRepositoryImpl implements DoctorRepository {

    @Override
    public Optional<Doctor> findById(String doctorId) {
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
    public void deleteById(String doctorId) {
        this.findById(doctorId).ifPresent(doctor -> DataHolder.doctors.remove(doctor));
    }
}

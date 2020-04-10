package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.service.DoctorService;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/doctor", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class DoctorApi {

    private final DoctorService doctorService;
    private final SpecializationService specializationService;

    public DoctorApi(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }


    @PostMapping
    public Doctor createDoctor(@RequestParam String name,
                               @RequestParam String address,
                               @RequestParam Integer consultancyFees,
                               @RequestParam String contactNo,
                               @RequestParam String email,
                               @RequestParam Long specializationId){

        Doctor doctor =  doctorService.createDoctor(name,address,consultancyFees,contactNo,email,specializationId);
        return doctor;
    }

    @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable Long doctorId){
        doctorService.deleteById(doctorId);
    }

    @GetMapping(params = "specializationName")
    public List<Doctor> getAllDoctorsBySpecialization(@RequestParam String specializationName){
        return doctorService.findAllBySpecializationName(specializationName);
    }

    @GetMapping("/{doctorId}/patients")
    public List<Patient> getPatients(@PathVariable Long doctorId){
        return doctorService.findPatientsByDoctorId(doctorId);
    }

    @GetMapping("/search/{doctorName}")
    public List<Doctor> searchDoctor(@PathVariable String doctorName){
        return doctorService.searchAllByName(doctorName);
    }

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.findAll();
    }

    @GetMapping(params = "doctorId")
    public Doctor getDoctor(@RequestParam Long doctorId){
        return doctorService.findById(doctorId);
    }


}

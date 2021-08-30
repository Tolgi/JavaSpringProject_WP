package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.service.DoctorService;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/doctor", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class DoctorApi {

    private final DoctorService doctorService;
    private final SpecializationService specializationService;

    public DoctorApi(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }

//    @PostMapping
//    public Doctor createDoctor(@RequestParam String name,
//                               @RequestParam String address,
//                               @RequestParam Integer consultancyFees,
//                               @RequestParam String contactNo,
//                               @RequestParam String email,
//                               @RequestParam Long specializationId){
//
//        Doctor doctor =  doctorService.createDoctor(name,address,consultancyFees,contactNo,email,specializationId);
//        return doctor;
//    }

    @DeleteMapping("/{doctorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDoctor(@PathVariable Long doctorId){
        doctorService.deleteById(doctorId);
    }

    @GetMapping(params = "specializationName")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<Doctor> getAllDoctorsBySpecialization(@RequestParam String specializationName){
        return doctorService.findAllBySpecializationName(specializationName);
    }

    @GetMapping("/{doctorId}/patients")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<Patient> getPatients(@PathVariable Long doctorId){
        return doctorService.findPatientsByDoctorId(doctorId);
    }

    @GetMapping("/search/{doctorName}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<Doctor> searchDoctor(@PathVariable String doctorName){
        return doctorService.searchAllByName(doctorName);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<Doctor> getAllDoctors(){
        return doctorService.findAll();
    }

    @GetMapping(params = "doctorId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public Doctor getDoctor(@RequestParam Long doctorId){
        return doctorService.findById(doctorId);
    }

    @PatchMapping("/edit/{doctorId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public Doctor editDoctor(@PathVariable Long doctorId,
                               @RequestParam String name,
                               @RequestParam String address,
                               @RequestParam Integer consultancyFees,
                               @RequestParam String contactNo,
                               @RequestParam String email,
                               @RequestParam Long specializationId){

        Doctor doctor =  doctorService.editDoctor(doctorId, name, address, consultancyFees, contactNo, email, specializationId);
        return doctor;
    }

    @GetMapping("/number")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer numberOfDoctors(){
        return doctorService.numbersOfDoctors();
    }
}

package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.exceptions.*;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.service.DoctorService;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/doctor", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class DoctorApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorApi.class);
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
        if (Objects.isNull(doctorId)) {
            LOGGER.error("Doctor id is null!");
            return;
        }
        doctorService.deleteById(doctorId);
    }

    @GetMapping(params = "specializationName")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllDoctorsBySpecialization(@RequestParam String specializationName){
        try {
            return ResponseEntity.ok().body(doctorService.findAllBySpecializationName(specializationName));
        } catch (SpecializationNameIsNullOrEmpty ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/{doctorId}/patients")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPatients(@PathVariable Long doctorId){
        try {
            return ResponseEntity.ok().body(doctorService.findPatientsByDoctorId(doctorId));
        } catch (DoctorIdIsNull | InvalidDoctor_ID ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/search/{doctorName}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> searchDoctor(@PathVariable String doctorName){
        try {
            return ResponseEntity.ok().body(doctorService.searchAllByName(doctorName));
        } catch (DoctorNameIsNullOrEmpty ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping(params = "doctorId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getDoctor(@RequestParam Long doctorId){
        try {
            return ResponseEntity.ok().body(doctorService.findById(doctorId));
        } catch (DoctorIdIsNull | InvalidDoctor_ID ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @PatchMapping("/edit/{doctorId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editDoctor(@PathVariable Long doctorId,
                               @RequestParam String name,
                               @RequestParam String address,
                               @RequestParam Integer consultancyFees,
                               @RequestParam String contactNo,
                               @RequestParam String email,
                               @RequestParam Long specializationId){
        try {
            return ResponseEntity.ok().body(doctorService.editDoctor(doctorId, name, address, consultancyFees, contactNo, email, specializationId));
        } catch (SpecializationIdIsNull | DoctorIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/number")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer numberOfDoctors(){
        return doctorService.numbersOfDoctors();
    }
}

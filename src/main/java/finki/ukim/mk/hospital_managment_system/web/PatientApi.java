package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidPatient_ID;
import finki.ukim.mk.hospital_managment_system.exceptions.PatientIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.service.PatientService;
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
@RequestMapping(path = "/api/patient", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PatientApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientApi.class);
    private final PatientService patientService;

    public PatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public Patient createPatient(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam Long ssn,
                                 @RequestParam String gender,
                                 @RequestParam String email,
                                 @RequestParam String address,
                                 @RequestParam Integer age,
                                 @RequestParam String contactNo,
                                 @RequestParam Long doctorId){
        return patientService.createPatient(id,name, ssn, gender, email, address, age, contactNo, doctorId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return ResponseEntity.ok().body(patientService.findAll());
    }

    @GetMapping(params = "patientId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPatient(@RequestParam Long patientId) {
        try {
            return ResponseEntity.ok().body(patientService.getPatient(patientId));
        } catch (PatientIdIsNull | InvalidPatient_ID ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @PatchMapping("/edit/{patientId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editPatient(@PathVariable Long patientId,
                               @RequestParam String name,
                               @RequestParam Long ssn,
                               @RequestParam String gender,
                               @RequestParam String email,
                               @RequestParam String address,
                               @RequestParam Integer age,
                               @RequestParam String contactNo){
        try {
            return ResponseEntity.ok().body(patientService.editPatient(patientId, name, ssn, gender, email, address, age, contactNo));
        } catch (PatientIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @DeleteMapping("/{patientId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePatient(@PathVariable Long patientId){
        if (Objects.isNull(patientId)) {
            LOGGER.error("Patient ID is null!");
            return;
        }
        patientService.deleteById(patientId);
    }

    @GetMapping("/number")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer numberOfPatients(){
        return patientService.numbersOfPatients();
    }
}

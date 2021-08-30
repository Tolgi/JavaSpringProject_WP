package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.service.PatientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/patient", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PatientApi {

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
    public List<Patient> getAllPatients(){
        return patientService.findAll();
    }

    @GetMapping(params = "patientId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public Patient getPatient(@RequestParam Long patientId) {
        return patientService.getPatient(patientId);
    }

    @PatchMapping("/edit/{patientId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Patient editPatient(@PathVariable Long patientId,
                               @RequestParam String name,
                               @RequestParam Long ssn,
                               @RequestParam String gender,
                               @RequestParam String email,
                               @RequestParam String address,
                               @RequestParam Integer age,
                               @RequestParam String contactNo){
        return patientService.editPatient(patientId, name, ssn, gender, email, address, age, contactNo);
    }

    @DeleteMapping("/{patientId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePatient(@PathVariable Long patientId){
        patientService.deleteById(patientId);
    }

    @GetMapping("/number")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer numberOfPatients(){
        return patientService.numbersOfPatients();
    }
}

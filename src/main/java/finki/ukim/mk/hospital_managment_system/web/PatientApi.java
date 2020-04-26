package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Patient;
import finki.ukim.mk.hospital_managment_system.service.PatientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public Patient createPatient(@RequestParam String name,
                                 @RequestParam Long ssn,
                                 @RequestParam String gender,
                                 @RequestParam String email,
                                 @RequestParam String address,
                                 @RequestParam Integer age,
                                 @RequestParam String contactNo,
                                 @RequestParam Long doctorId){
        return patientService.createPatient(name, ssn, gender, email, address, age, contactNo, doctorId);
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.findAll();
    }

    @GetMapping(params = "patientId")
    public Patient getPatient(@RequestParam Long patientId) {
        return patientService.getPatient(patientId);
    }

    @PatchMapping("/edit/{patientId}")
    public Patient editPatient(@PathVariable Long patientId,
                               @RequestParam String name,
                               @RequestParam Long ssn,
                               @RequestParam String gender,
                               @RequestParam String email,
                               @RequestParam String address,
                               @RequestParam Integer age,
                               @RequestParam String contactNo,
                               @RequestParam Long doctorId){
        return patientService.editPatient(patientId, name, ssn, gender, email, address, age, contactNo, doctorId);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable Long patientId){
        patientService.deleteById(patientId);
    }

    @GetMapping("/number")
    public Integer numberOfPatients(){
        return patientService.numbersOfPatients();
    }
}

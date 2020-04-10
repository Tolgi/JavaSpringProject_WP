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
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime creationDate,
                                 @RequestParam Long doctorId){
        return patientService.createPatient(name, ssn, gender, email, address, age, contactNo, creationDate, doctorId);
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.findAll();
    }


    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable Long patientId){
        patientService.deleteById(patientId);
    }

}

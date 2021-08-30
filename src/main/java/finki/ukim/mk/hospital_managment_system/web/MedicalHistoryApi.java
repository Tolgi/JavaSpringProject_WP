package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.service.MedicalHistoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/medicalHistory", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MedicalHistoryApi {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryApi(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public MedicalHistory createMedicalHistory(@RequestParam String bloodPressure,
                                               @RequestParam Integer weight,
                                               @RequestParam Integer bloodSugar,
                                               @RequestParam String bodyTemperature,
                                               @RequestParam String medicalPrescription,
                                               @RequestParam Long patientId){

        return medicalHistoryService.createMedicalHistory(bloodPressure, weight, bloodSugar, bodyTemperature, medicalPrescription, patientId);
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<MedicalHistory> getAllMedicalHistoriesByPatientId(@PathVariable Long patientId){
        return medicalHistoryService.findAllByPatientId(patientId);
    }

    @DeleteMapping("/{medicalHistoryId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public void deleteMedicalHistory(@PathVariable Long medicalHistoryId){
        medicalHistoryService.deleteById(medicalHistoryId);
    }
}

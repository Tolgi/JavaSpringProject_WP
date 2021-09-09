package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.exceptions.MedicationHistoryIdIsNull;
import finki.ukim.mk.hospital_managment_system.exceptions.PatientIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.service.MedicalHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/medicalHistory", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MedicalHistoryApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalHistoryApi.class);
    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryApi(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> createMedicalHistory(@RequestParam String bloodPressure,
                                               @RequestParam Integer weight,
                                               @RequestParam Integer bloodSugar,
                                               @RequestParam String bodyTemperature,
                                               @RequestParam String medicalPrescription,
                                               @RequestParam Long patientId){
        try {
            return ResponseEntity.ok(medicalHistoryService.createMedicalHistory(bloodPressure, weight, bloodSugar, bodyTemperature, medicalPrescription, patientId));
        } catch (PatientIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllMedicalHistoriesByPatientId(@PathVariable Long patientId){
        try {
            return ResponseEntity.ok(medicalHistoryService.findAllByPatientId(patientId));
        } catch (PatientIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @DeleteMapping("/{medicalHistoryId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public void deleteMedicalHistory(@PathVariable Long medicalHistoryId){
        try {
            medicalHistoryService.deleteById(medicalHistoryId);
        } catch (MedicationHistoryIdIsNull ex) {
            LOGGER.error(ex.getMessage());
            return;
        }
    }
}

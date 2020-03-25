package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.MedicalHistory;
import finki.ukim.mk.hospital_managment_system.service.MedicalHistoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/medicalHistory", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MedicalHistoryApi {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryApi(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping
    public MedicalHistory createMedicalHistory(@RequestParam String bloodPressure,
                                               @RequestParam Integer weight,
                                               @RequestParam Integer bloodSugar,
                                               @RequestParam String bodyTemperature,
                                               @RequestParam String medicalPrescription,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime visitDate,
                                               @RequestParam Long patientId){

        return medicalHistoryService.createMedicalHistory(bloodPressure, weight, bloodSugar, bodyTemperature, medicalPrescription, visitDate, patientId);
    }

    @GetMapping("/{patientId}")
    public List<MedicalHistory> getAllMedicalHistoriesByPatientId(@PathVariable Long patientId){
        return medicalHistoryService.findAllByPatientId(patientId);
    }

    @DeleteMapping("/{medicalHistoryId}")
    public void deleteMedicalHistory(@PathVariable Long medicalHistoryId){
        medicalHistoryService.deleteById(medicalHistoryId);
    }
}

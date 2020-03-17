package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalHistory {

    private Long id;
    private String bloodPressure;
    private Integer weight;
    private Integer bloodSugar;
    private String bodyTemperature;
    private String medicalPrescription;
    private LocalDateTime visitDate;

    private Patient patient;

    public void createMedicalHistory(String bloodPressure, Integer weight, Integer bloodSugar, String bodyTemperature, String medicalPrescription, LocalDateTime visitDate, Patient patient){
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.bloodSugar = bloodSugar;
        this.bodyTemperature = bodyTemperature;
        this.medicalPrescription = medicalPrescription;
        this.visitDate = visitDate;
        this.patient = patient;
    }

}

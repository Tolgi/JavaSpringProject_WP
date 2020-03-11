package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalHistory {

    private String id;
    private String bloodPressure;
    private Integer weight;
    private Integer bloodSugar;
    private String bodyTemperature;
    private String medicalPrescription;
    private LocalDateTime visitDate;

    private Patient patient;

}

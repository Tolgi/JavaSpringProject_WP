package finki.ukim.mk.hospital_managment_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "medicalhistory")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    private String bloodPressure;
    private Integer weight;
    private Integer bloodSugar;
    private String bodyTemperature;
    private String medicalPrescription;
    private LocalDateTime visitDate;
    private String patientName;
    private Long patientSsn;

    @ManyToOne
    private Patient patient;

    public void createMedicalHistory(String bloodPressure, Integer weight, Integer bloodSugar, String bodyTemperature, String medicalPrescription, LocalDateTime visitDate, Patient patient){
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.bloodSugar = bloodSugar;
        this.bodyTemperature = bodyTemperature;
        this.medicalPrescription = medicalPrescription;
        this.visitDate = visitDate;
        this.patient = patient;
        this.patientName = patient.getName();
        this.patientSsn = patient.getSsn();
    }

}

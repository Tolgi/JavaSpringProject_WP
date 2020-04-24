package finki.ukim.mk.hospital_managment_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "appointment_fees")
    private Integer consultancyFees;

    @Column(name = "appointment_status")
    private String status;

    @Column(name = "appointment_date")
    private LocalDate date;

    @Column(name = "appointment_time")
    private LocalTime time;


    private LocalDateTime creationDate;

    private String doctorName;

    private Long patientSsn;


    @ManyToOne
    private Patient patient;


    @ManyToOne
    private Doctor doctor;

    public void createAppointment(String status, LocalDate date, LocalTime time, LocalDateTime creationDate, Patient patient, Doctor doctor){
        this.status = status;
        this.date = date;
        this.time = time;
        this.creationDate = creationDate;
        this.patient = patient;
        this.doctor = doctor;
        this.doctorName = doctor.getName();
        this.patientSsn = patient.getSsn();
        this.consultancyFees = doctor.getConsultancyFees();
    }


}

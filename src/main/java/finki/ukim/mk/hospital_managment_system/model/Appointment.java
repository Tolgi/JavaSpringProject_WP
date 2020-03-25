package finki.ukim.mk.hospital_managment_system.model;

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
    private Long id;
    private Integer consultancyFees;
    private String status;
    private LocalDate date;

    @Column(name = "from_time")
    private LocalTime time;


    private LocalDateTime creationDate;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    public void createAppointment(Integer consultancyFees, String status, LocalDate date, LocalTime time, LocalDateTime creationDate, Patient patient, Doctor doctor){
        this.consultancyFees = consultancyFees;
        this.status = status;
        this.date = date;
        this.time = time;
        this.creationDate = creationDate;
        this.patient = patient;
        this.doctor = doctor;
    }


}

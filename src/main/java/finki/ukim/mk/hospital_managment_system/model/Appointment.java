package finki.ukim.mk.hospital_managment_system.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    private Long id;
    private Integer consultancyFees;
    private String status;
    private LocalDate date;
    private LocalTime time;


    private LocalDateTime creationDate;

    private Patient patient;
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

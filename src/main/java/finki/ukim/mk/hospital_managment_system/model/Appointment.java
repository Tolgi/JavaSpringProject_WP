package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    private String id;
    private Integer consultancyFees;
    private String status;
    private LocalDate date;
    private LocalTime time;


    private LocalDateTime creationDate;

    private Patient patient;
    private Doctor doctor;

}

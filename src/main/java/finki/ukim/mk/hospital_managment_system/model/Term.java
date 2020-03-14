package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Term {

   private String id;
   private LocalDate date;
   private LocalTime timeOfAdmission;
   private String status;

   private Doctor doctor;

}

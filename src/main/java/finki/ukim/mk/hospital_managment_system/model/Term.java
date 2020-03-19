package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Term {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private LocalDate date;
   private LocalTime timeOfAdmission;
   private String status;

   @ManyToOne
   private Doctor doctor;

   public void createTerm(LocalDate date, LocalTime timeOfAdmission, String status, Doctor doctor){
      this.date = date;
      this.timeOfAdmission = timeOfAdmission;
      this.status = status;
      this.doctor = doctor;
   }

}

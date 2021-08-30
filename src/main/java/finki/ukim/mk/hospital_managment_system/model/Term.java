package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
   @Column(name = "term_id")
   private Long id;

   @Column(name = "term_date")
   private LocalDate date;

   private LocalTime timeOfAdmission;

   @Column(name = "term_status")
   private String status;

   @ManyToOne
   @OnDelete(action = OnDeleteAction.CASCADE)
   private Doctor doctor;

   public void createTerm(LocalDate date, LocalTime timeOfAdmission, String status, Doctor doctor){
      this.date = date;
      this.timeOfAdmission = timeOfAdmission;
      this.status = status;
      this.doctor = doctor;
   }
}

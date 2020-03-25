package finki.ukim.mk.hospital_managment_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long ssn;
    private String gender;
    private String email;
    private String address;
    private Integer age;
    private String contactNo;

    private LocalDateTime creationDate;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "patients")
   // @NotFound(action = NotFoundAction.IGNORE)
    private List<Doctor> familyDoctors;

    public void createPatient(String name, Long ssn, String gender, String email, String address, Integer age, String contactNo, LocalDateTime creationDate){
        this.name = name;
        this.ssn = ssn;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.age = age;
        this.contactNo = contactNo;
        this.creationDate = creationDate;
        familyDoctors = new ArrayList<>();
    }

}

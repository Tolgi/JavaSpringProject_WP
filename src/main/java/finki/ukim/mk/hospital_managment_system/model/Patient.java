package finki.ukim.mk.hospital_managment_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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
    @Column(name = "patient_id")
    private Long id;

    @Column(name = "patient_name")
    private String name;

    @Column(name = "patient_ssn")
    private Long ssn;

    @Column(name = "patient_gender")
    private String gender;

    @Column(name = "patient_email")
    private String email;

    @Column(name = "patient_address")
    private String address;

    @Column(name = "patient_age")
    private Integer age;

    @Column(name = "patient_number")
    private String contactNo;

    @Column(name = "patient_creation_date")
    private LocalDateTime creationDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "patients")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Doctor> familyDoctors;

    public void createPatient(Long id, String name, Long ssn, String gender, String email, String address, Integer age, String contactNo, LocalDateTime creationDate){
        this.id = id;
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

    @PreRemove
    private void removeDoctors(){
        for (Doctor d : familyDoctors) {
            d.getPatients().remove(this);
        }
    }

    public void setFamilyDoctors(Doctor familyDoctor) {
        this.familyDoctors.add(familyDoctor);
    }
}

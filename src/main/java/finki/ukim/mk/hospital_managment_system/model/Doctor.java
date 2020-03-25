package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Integer consultancyFees;
    private String contactNo;
    private String email;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Specialization specialization;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Patient> patients;

    public void follow(Patient patient){
        this.patients.add(patient);
        patient.getFamilyDoctors().add(this);
    }

    public void unfollow(Patient patient){
        this.patients.remove(patient);
        patient.getFamilyDoctors().remove(this);
    }

    public void createDoctor(String name, String address, Integer consultancyFees, String contactNo, String email, Specialization specialization){
        this.name = name;
        this.address = address;
        this.consultancyFees = consultancyFees;
        this.contactNo = contactNo;
        this.email = email;
        this.specialization = specialization;
        patients = new ArrayList<>();
    }
}

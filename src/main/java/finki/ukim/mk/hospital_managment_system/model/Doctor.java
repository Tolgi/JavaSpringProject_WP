package finki.ukim.mk.hospital_managment_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    private Long id;

    @Column(name = "doctor_name")
    private String name;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_fees")
    private Integer consultancyFees;

    @Column(name = "doctor_number")
    private String contactNo;

    @Column(name = "doctor_email")
    private String email;

    @ManyToOne
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

    public void createDoctor(Long id, String name, String address, Integer consultancyFees, String contactNo, String email, Specialization specialization){
        this.id = id;
        this.name = name;
        this.address = address;
        this.consultancyFees = consultancyFees;
        this.contactNo = contactNo;
        this.email = email;
        this.specialization = specialization;
        patients = new ArrayList<>();
    }
}

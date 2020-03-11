package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {

    private String id;
    private String name;
    private String address;
    private Integer consultancyFees;
    private String contactNo;
    private String email;

    private Specialization specialization;
    private List<Patient> patients;
}

package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {

    private String id;
    private String name;
    private String gender;
    private String email;
    private String address;
    private Integer age;
    private String contactNo;

    private LocalDateTime creationDate;

    private List<Doctor> familyDoctors;

}

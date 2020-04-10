package finki.ukim.mk.hospital_managment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private Long id;

    @Column(name = "specialization_name")
    private String name;

    @Column(name = "specialization_creationtime")
    private LocalDateTime creationTime;



    public void createSpecialization(String name, LocalDateTime creationTime){
        this.name = name;
        this.creationTime = creationTime;
    }
}

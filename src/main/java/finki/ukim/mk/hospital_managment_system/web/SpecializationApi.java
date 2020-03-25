package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/specialization")
public class SpecializationApi {

    private final SpecializationService specializationService;

    public SpecializationApi(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }


    @PostMapping
    public Specialization createSpecialization(@RequestParam String name,
                                               @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime creationDate){
        Specialization specialization = specializationService.createSpecialization(name, creationDate);
        return specialization;
    }

    @GetMapping
    public List<Specialization> getAllSpecializations(){
        return specializationService.findAll();
    }

    @DeleteMapping("/{specializationId}")
    public void deleteSpecialization(@PathVariable Long specializationId){
        specializationService.deleteById(specializationId);
    }
}

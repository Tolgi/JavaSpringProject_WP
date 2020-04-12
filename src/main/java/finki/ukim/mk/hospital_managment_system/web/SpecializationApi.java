package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/specialization")
public class SpecializationApi {

    private final SpecializationService specializationService;

    public SpecializationApi(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping("/{specializationId}")
    public Specialization getSpecialization(@PathVariable Long specializationId) {
        return specializationService.findById(specializationId);
    }

    @PostMapping
    public Specialization createSpecialization(@RequestParam String name){
        Specialization specialization = specializationService.createSpecialization(name);
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

    @PatchMapping("/edit/{specializationId}")
    public Specialization editSpecialization(@PathVariable Long specializationId, @RequestParam String name){
        Specialization specialization = specializationService.editSpecialization(specializationId, name);
        return specialization;
    }
}

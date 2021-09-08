package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.exceptions.SpecializationIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/specialization")
public class SpecializationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecializationApi.class);
    private final SpecializationService specializationService;

    public SpecializationApi(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping("/{specializationId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public Specialization getSpecialization(@PathVariable Long specializationId) {
        return specializationService.findById(specializationId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Specialization createSpecialization(@RequestParam String name){
        Specialization specialization = specializationService.createSpecialization(name);
        return specialization;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<Specialization> getAllSpecializations(){
        return specializationService.findAll();
    }

    @DeleteMapping("/{specializationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSpecialization(@PathVariable Long specializationId){
        try {
            specializationService.deleteById(specializationId);
        } catch (SpecializationIdIsNull ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @PatchMapping("/edit/{specializationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Specialization editSpecialization(@PathVariable Long specializationId, @RequestParam String name){
        Specialization specialization = specializationService.editSpecialization(specializationId, name);
        return specialization;
    }
}

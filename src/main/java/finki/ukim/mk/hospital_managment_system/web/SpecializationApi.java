package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.exceptions.SpecializationIdIsNull;
import finki.ukim.mk.hospital_managment_system.model.Specialization;
import finki.ukim.mk.hospital_managment_system.service.SpecializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getSpecialization(@PathVariable Long specializationId) {
        try {
            return ResponseEntity.ok().body(specializationService.findById(specializationId));
        } catch (SpecializationIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Specialization createSpecialization(@RequestParam String name){
        return specializationService.createSpecialization(name);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Specialization>> getAllSpecializations(){
        return ResponseEntity.ok(specializationService.findAll());
    }

    @DeleteMapping("/{specializationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSpecialization(@PathVariable Long specializationId){
        try {
            specializationService.deleteById(specializationId);
        } catch (SpecializationIdIsNull ex) {
            LOGGER.error(ex.getMessage());
            return;
        }
    }

    @PatchMapping("/edit/{specializationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editSpecialization(@PathVariable Long specializationId, @RequestParam String name){
        try {
            return ResponseEntity.ok(specializationService.editSpecialization(specializationId, name));
        } catch (SpecializationIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
}

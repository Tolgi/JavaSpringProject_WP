package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.exceptions.DoctorIdIsNull;
import finki.ukim.mk.hospital_managment_system.exceptions.InvalidTermStatus;
import finki.ukim.mk.hospital_managment_system.exceptions.ScheduledTerm;
import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.service.TermService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/term", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class TermApi {

    private final TermService termService;

    public TermApi(TermService termService) {
        this.termService = termService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> createTerm(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime time,
                                           @RequestParam String status,
                                           @RequestParam Long doctorId){
        try {
            Term term = termService.createTerm(date, time, status, doctorId);
            return ResponseEntity.ok().body(term);
        } catch (ScheduledTerm | DoctorIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> getAllTerms(){
        return ResponseEntity.ok().body(termService.findAll());
    }

    @GetMapping(params = "doctorId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> getTermsByDoctorId(@RequestParam Long doctorId){
        try {
            List<Term> termsByDoctor = termService.findAllByDoctorId(doctorId);
            return ResponseEntity.ok().body(termsByDoctor);
        } catch (DoctorIdIsNull ex) {
           return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping(params = "status")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> getTermsByStatus(@RequestParam String status){
        try {
           return ResponseEntity.ok().body(termService.findAllByStatus(status));
        } catch (InvalidTermStatus ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @GetMapping("/{doctorId}/{status}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> getTermsByDoctorIdAndStatus(@PathVariable Long doctorId,
                                                  @PathVariable String status){
        try {
            return ResponseEntity.ok().body(termService.findAllByDoctorIdAndByStatus(doctorId, status));
        } catch (DoctorIdIsNull | InvalidTermStatus ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    @DeleteMapping("/{termId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public void deleteTerm(@PathVariable Long termId){
        termService.deleteById(termId);
    }
}

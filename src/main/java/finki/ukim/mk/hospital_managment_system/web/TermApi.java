package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Term;
import finki.ukim.mk.hospital_managment_system.service.TermService;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Term createTerm(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime time,
                           @RequestParam String status,
                           @RequestParam Long doctorId){
        return termService.createTerm(date, time, status, doctorId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public List<Term> getAllTerms(){
        return termService.findAll();
    }

    @GetMapping(params = "doctorId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public List<Term> getTermsByDoctorId(@RequestParam Long doctorId){
        return termService.findAllByDoctorId(doctorId);
    }

    @GetMapping(params = "status")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public List<Term> getTermsByStatus(@RequestParam String status){
        return termService.findAllByStatus(status);
    }

    @GetMapping("/{doctorId}/{status}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public List<Term> getTermsByDoctorIdAndStatus(@PathVariable Long doctorId,
                                                  @PathVariable String status){
        return termService.findAllByDoctorIdAndByStatus(doctorId, status);
    }

    @DeleteMapping("/{termId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public void deleteTerm(@PathVariable Long termId){
        termService.deleteById(termId);
    }
}

package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.exceptions.*;
import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/appointment", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AppointmentApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentApi.class);
    private final AppointmentService appointmentService;

    public AppointmentApi(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping(params = "appointmentId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAppointment(@RequestParam Long appointmentId){
        try {
            return ResponseEntity.ok().body(appointmentService.findById(appointmentId));
        } catch (AppointmentIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(params = "patientId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> getAllAppointmentsByPatientId(@RequestParam Long patientId){
        try {
            return ResponseEntity.ok(appointmentService.findAllByPatientId(patientId));
        } catch (PatientIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(params = "doctorId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> getAllAppointmentsByDoctorId(@RequestParam Long doctorId){
        try {
            return ResponseEntity.ok(appointmentService.findAllByDoctorId(doctorId));
        } catch (DoctorIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> createAppointment(@RequestParam("patientId") Long patientId,
                                         @RequestParam("status") String status,
                                         @RequestParam("doctorId") Long doctorId,
                                         @RequestParam("termId") Long termId){

        try {
            return ResponseEntity.ok(appointmentService.createAppointment(status, patientId, doctorId, termId));
        } catch (PatientIdIsNull | DoctorIdIsNull | TermIdIsNull | ComponentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{appointmentId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public void deleteAppointment(@PathVariable Long appointmentId){
        try {
            appointmentService.deleteById(appointmentId);
        } catch (AppointmentIdIsNull ex) {
            LOGGER.error(ex.getMessage());
            return;
        }
    }

    @PatchMapping("/{appointmentId}/{status}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> updateStatus(@PathVariable Long appointmentId, @PathVariable String status){
        try {
            return ResponseEntity.ok(appointmentService.updateStatus(appointmentId, status));
        } catch (AppointmentIdIsNull ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/number")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer numberOfAppointments(){
        return appointmentService.numberOfAppointments();
    }
}
package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/appointment", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AppointmentApi {

    private final AppointmentService appointmentService;

    public AppointmentApi(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<Appointment> getAllAppointments(){
        return appointmentService.findAll();
    }

    @GetMapping(params = "appointmentId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public Appointment getAppointment(@RequestParam Long appointmentId){
        return appointmentService.findById(appointmentId);
    }

    @GetMapping(params = "patientId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public List<Appointment> getAllAppointmentsByPatientId(@RequestParam Long patientId){
        return appointmentService.findAllByPatientId(patientId);
    }


    @GetMapping(params = "doctorId")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public List<Appointment> getAllAppointmentsByDoctorId(@RequestParam Long doctorId){
        return appointmentService.findAllByDoctorId(doctorId);
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public Appointment createAppointment(@RequestParam("patientId") Long patientId,
                                         @RequestParam("status") String status,
                                         @RequestParam("doctorId") Long doctorId,
                                         @RequestParam("termId") Long termId){

        Appointment appointment = appointmentService.createAppointment(status, patientId, doctorId, termId);
        return appointment;

    }

    @DeleteMapping("/{appointmentId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public void deleteAppointment(@PathVariable Long appointmentId){
        appointmentService.deleteById(appointmentId);
    }

    @PatchMapping("/{appointmentId}/{status}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    public Appointment updateStatus(@PathVariable Long appointmentId, @PathVariable String status){
        return appointmentService.updateStatus(appointmentId, status);
    }

    @GetMapping("/number")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer numberOfAppointments(){
        return appointmentService.numberOfAppointments();
    }

}

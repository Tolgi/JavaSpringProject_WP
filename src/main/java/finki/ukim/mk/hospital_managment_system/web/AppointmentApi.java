package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Appointment;
import finki.ukim.mk.hospital_managment_system.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public List<Appointment> getAllAppointments(){
        return appointmentService.findAll();
    }

    @GetMapping(params = "appointmentId")
    public Appointment getAppointment(@RequestParam Long appointmentId){
        return appointmentService.findById(appointmentId);
    }

    @GetMapping(params = "patientId")
    public List<Appointment> getAllAppointmentsByPatientId(@RequestParam Long patientId){
        return appointmentService.findAllByPatientId(patientId);
    }


    @GetMapping(params = "doctorId")
    public List<Appointment> getAllAppointmentsByDoctorId(@RequestParam Long doctorId){
        return appointmentService.findAllByDoctorId(doctorId);
    }


    @PostMapping
    public Appointment createAppointment(@RequestParam("patientId") Long patientId,
                                         @RequestParam("consultancyFees") Integer consultancyFees,
                                         @RequestParam("status") String status,
                                         @RequestParam(value = "creationDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime creationDate,
                                         @RequestParam("doctorId") Long doctorId,
                                         @RequestParam("termId") Long termId){

        Appointment appointment = appointmentService.createAppointment(consultancyFees, status, creationDate, patientId, doctorId, termId);
        return appointment;

    }

    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@PathVariable Long appointmentId){
        appointmentService.deleteById(appointmentId);
    }


}

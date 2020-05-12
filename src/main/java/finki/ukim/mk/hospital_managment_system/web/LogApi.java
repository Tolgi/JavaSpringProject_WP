package finki.ukim.mk.hospital_managment_system.web;


import finki.ukim.mk.hospital_managment_system.model.Log;
import finki.ukim.mk.hospital_managment_system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(value = "/logs")
public class LogApi {

    private LogService logService;

    public LogApi(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Log> getLogs() {
        return this.logService.getAllLogs();
    }

}
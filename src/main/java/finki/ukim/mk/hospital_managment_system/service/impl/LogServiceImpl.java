package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidLogException;
import finki.ukim.mk.hospital_managment_system.model.Log;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaLogRepository;
import finki.ukim.mk.hospital_managment_system.service.LogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final JpaLogRepository logRepository;

    public LogServiceImpl(JpaLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log findById(Long logId) {
        return logRepository.findById(logId).orElseThrow(InvalidLogException::new);
    }

    @Override
    public Log createLog(String sessionId, String roll, String userName, String ipAddress, boolean isSuccess, String signUpTime, String logOutTime, double totalHours) {
        Log log = new Log();
        log.createLog(sessionId, roll, userName, ipAddress, isSuccess, signUpTime, logOutTime, totalHours);
        return logRepository.save(log);
    }

    @Override
    public void update(Log log) {
        logRepository.save(log);
    }

    @Override
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }
}

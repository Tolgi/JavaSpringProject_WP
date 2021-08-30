package finki.ukim.mk.hospital_managment_system.service;
import finki.ukim.mk.hospital_managment_system.model.Log;
import java.util.List;

public interface LogService {

    Log findById(Long logId);

    Log createLog(String sessionId, String roll, String userName, String ipAddress, boolean isSuccess, String signUpTime, String logOutTime, double totalHours);

    List<Log> getAllLogs();

    void update(Log log);
}

package finki.ukim.mk.hospital_managment_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HospitalManagmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagmentSystemApplication.class, args);
    }

}

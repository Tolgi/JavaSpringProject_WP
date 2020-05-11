package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.ApplicationUser;
import finki.ukim.mk.hospital_managment_system.model.Doctor;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaUserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserApi {
    private JpaUserRepository userRepository;

    public UserApi(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<ApplicationUser> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/number")
    public Integer numbersOfUsers() {
        List<ApplicationUser> users = userRepository.findAll();
        Integer number = users.size();
        return number;
    }
}

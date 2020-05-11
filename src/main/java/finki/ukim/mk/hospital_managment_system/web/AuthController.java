package finki.ukim.mk.hospital_managment_system.web;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import finki.ukim.mk.hospital_managment_system.exceptions.InvalidDoctorId;
import finki.ukim.mk.hospital_managment_system.model.*;
import finki.ukim.mk.hospital_managment_system.repository.DoctorRepository;
import finki.ukim.mk.hospital_managment_system.repository.PatientRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaRoleRepository;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaUserRepository;
import finki.ukim.mk.hospital_managment_system.security.jwt.JwtUtils;
import finki.ukim.mk.hospital_managment_system.security.payload.request.LoginRequest;
import finki.ukim.mk.hospital_managment_system.security.payload.request.SignupRequestAdmin;
import finki.ukim.mk.hospital_managment_system.security.payload.request.SignupRequestDoctor;
import finki.ukim.mk.hospital_managment_system.security.payload.request.SignupRequestPatient;
import finki.ukim.mk.hospital_managment_system.security.payload.response.JwtResponse;
import finki.ukim.mk.hospital_managment_system.security.payload.response.MessageResponse;
import finki.ukim.mk.hospital_managment_system.service.DoctorService;
import finki.ukim.mk.hospital_managment_system.service.PatientService;
import finki.ukim.mk.hospital_managment_system.service.impl.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JpaUserRepository userRepository;
    private JpaRoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AuthController(AuthenticationManager authenticationManager, JpaUserRepository userRepository, JpaRoleRepository roleRepository,
                          PasswordEncoder encoder, JwtUtils jwtUtils, DoctorService doctorService, PatientService patientService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Bad credentials, please try again!"));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }



    @PostMapping("/patient/signup")
    public ResponseEntity<?> registerPatientUser(@RequestBody SignupRequestPatient signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
            ApplicationUser user = new ApplicationUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "doctor":
                        Role modRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        ApplicationUser tmpUser = userRepository.findByUsername(signUpRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + signUpRequest.getUsername()));



        //Create new patient object
       patientService.createPatient(tmpUser.getId(), signUpRequest.getName(), signUpRequest.getSsn(), signUpRequest.getGender(),
               signUpRequest.getEmail(), signUpRequest.getAddress(), signUpRequest.getAge(), signUpRequest.getContactNo(),
               signUpRequest.getDoctorId());


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/doctor/signup")
    public ResponseEntity<?> registerDoctorUser(@RequestBody SignupRequestDoctor signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        ApplicationUser user = new ApplicationUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "doctor":
                        Role modRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        ApplicationUser tmpUser = userRepository.findByUsername(signUpRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + signUpRequest.getUsername()));



        //Create new doctor object
        doctorService.createDoctor(tmpUser.getId(), signUpRequest.getName(), signUpRequest.getAddress(), signUpRequest.getConsultancyFees(),
                signUpRequest.getContactNo(), signUpRequest.getEmail(), signUpRequest.getSpecializationId());


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerAdmin(@RequestBody SignupRequestAdmin signUpRequest) {

        // Create new user's account
        ApplicationUser user = new ApplicationUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "doctor":
                        Role modRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
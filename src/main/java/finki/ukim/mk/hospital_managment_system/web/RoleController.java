package finki.ukim.mk.hospital_managment_system.web;

import finki.ukim.mk.hospital_managment_system.model.Role;
import finki.ukim.mk.hospital_managment_system.model.ERole;
import finki.ukim.mk.hospital_managment_system.service.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/auth/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public Role signUp(@RequestParam ERole name) {
        return roleService.createRole(name);
    }
}

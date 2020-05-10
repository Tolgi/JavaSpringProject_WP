package finki.ukim.mk.hospital_managment_system.service.impl;

import finki.ukim.mk.hospital_managment_system.model.Role;
import finki.ukim.mk.hospital_managment_system.model.ERole;
import finki.ukim.mk.hospital_managment_system.repository.jpa.JpaRoleRepository;
import finki.ukim.mk.hospital_managment_system.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private JpaRoleRepository jpaRoleRepository;

    public RoleServiceImpl(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Role createRole(ERole name) {
        Role role = new Role();
        role.setName(name);
        jpaRoleRepository.save(role);
        return role;
    }
}

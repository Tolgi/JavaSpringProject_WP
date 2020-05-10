package finki.ukim.mk.hospital_managment_system.service;

import finki.ukim.mk.hospital_managment_system.model.Role;
import finki.ukim.mk.hospital_managment_system.model.ERole;

public interface RoleService {

    Role createRole(ERole name);
}

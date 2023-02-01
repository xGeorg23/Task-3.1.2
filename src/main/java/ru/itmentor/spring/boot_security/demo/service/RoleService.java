package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Set<Role> setRole(List<String> rolesId);
}

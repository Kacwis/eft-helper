package com.example.efthelper.model.repository;

import com.example.efthelper.model.ERole;
import com.example.efthelper.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    List<Role> findAll();

    Optional<Role> findByRole(ERole role);

    Role save(Role role);

}

package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.Role;
import com.example.efthelper.model.repository.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, Integer>, RoleRepository {
}

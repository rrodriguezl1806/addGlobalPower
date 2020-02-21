package com.example.addGlobalPower.repositories;


import com.example.addGlobalPower.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(@Param("name") String name);
}

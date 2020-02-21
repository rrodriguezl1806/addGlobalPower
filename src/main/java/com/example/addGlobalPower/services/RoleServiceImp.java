package com.example.addGlobalPower.services;

import com.example.addGlobalPower.entities.Role;
import com.example.addGlobalPower.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByRoleName(name);
    }
}

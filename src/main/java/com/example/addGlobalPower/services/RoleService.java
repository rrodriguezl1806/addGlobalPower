package com.example.addGlobalPower.services;


import com.example.addGlobalPower.entities.Role;

public interface RoleService {
    Role findByName(String name);
}

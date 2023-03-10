package com.example.spacenter.service;

import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.RoleRepository;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public RoleService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public void addRoleToUser(String username, String role) {

        UserEntity user = this.userRepository.findByUsername(username).orElse(null);


//        if (!user.getRoles().stream().filter(userRole -> user.getId().equals(role))) {
//            Role roleEntity =
//                    this.roleRepository.findByName(role);
//
//
//
//            user.setRoles(roleEntity);
//            this.userRepository.saveAndFlush(user);
//        }

    }
}

package com.example.spacenter.service;

import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.RoleRepository;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserDetailsService detailsService;

    @Autowired
    public RoleService(UserRepository userRepository, RoleRepository roleRepository, UserDetailsService detailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.detailsService = detailsService;
    }


    public void addRoleToUser(String email, String role) {

        UserEntity user = this.userRepository.findByEmail(email).orElse(null);


        assert user != null;
        UserDetails details = detailsService.loadUserByUsername(user.getEmail());
        String currentRole = "";
        if (details.getAuthorities().size() < 2) {

            currentRole = details.getAuthorities().toString();
            currentRole = currentRole.replaceAll("\\[", "").replaceAll("\\]","");
        }
        if (details.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_" + role))) {

            Role roleEntity = this.roleRepository.findByName(role);

            currentRole = currentRole.split("_")[1];

            Role roleToRemove = this.roleRepository.findByName(currentRole);

            List<Role> userRoles = user.getRoles().stream().toList();

            for (Role userRole : userRoles) {
                if(userRole.getName().equals(roleToRemove.getName())){
                    user.removeRole(userRole);
                }
            }
            user.addRole(roleEntity);
            this.userRepository.saveAndFlush(user);
        }

    }

    public void removeRoleFromUser(String email, String role) {

        UserEntity user = this.userRepository.findByEmail(email).orElse(null);

        assert user != null;
        UserDetails details = detailsService.loadUserByUsername(user.getEmail());
//        if (details.getAuthorities().stream()
//                .noneMatch(a -> a.getAuthority().equals(role))) {

        Role roleEntity = this.roleRepository.findByName(role);
        user.removeRole(roleEntity);
        this.userRepository.saveAndFlush(user);
//        }
    }
}

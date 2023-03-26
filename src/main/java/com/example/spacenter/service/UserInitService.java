package com.example.spacenter.service;

import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.RoleEnum;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.RoleRepository;
import com.example.spacenter.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserInitService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;


    public UserInitService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initUsers() {
        if (userRepository.count() == 0) {
            initRoles();
            initAdmin();
            initModerator();
            initUser();
        }
    }

    private void initRoles() {
        if (this.roleRepository.count() == 0) {
            Arrays.stream(RoleEnum.values())
                    .forEach(roleEnum -> {
                        this.roleRepository.save(new Role(roleEnum.name()));
                    });
        }
    }


    private void initAdmin() {

        Role adminRole = this.roleRepository.findByName("ADMIN");

        var admin = new UserEntity();
        if (checkExistence("admin@com.com", "admin")) return;
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.setEmail("admin@com.com");
        admin.addRole(adminRole);

        this.userRepository.save(admin);

    }

    private void initModerator() {

        Role moderatorRole = this.roleRepository.findByName("MODERATOR");

        var moderator = new UserEntity();
        if (checkExistence("moderator@com.com", "moderator")) return;
        moderator.setUsername("moderator");
        moderator.setPassword(passwordEncoder.encode("123"));
        moderator.setFirstName("Moderator");
        moderator.setLastName("Moderatorov");
        moderator.setEmail("moderator@com.com");
        moderator.addRole(moderatorRole);

        this.userRepository.save(moderator);
    }

    private void initUser() {

        Role userRole = this.roleRepository.findByName("USER");

        var user = new UserEntity();
        if (checkExistence("user@com.com", "user")) return;
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123"));
        user.setFirstName("Petar");
        user.setLastName("Petrov");
        user.setEmail("user@com.com");
        user.addRole(userRole);

        this.userRepository.save(user);

    }

    private boolean checkExistence(String email, String username) {
        Optional<UserEntity> entityEmail = this.userRepository.findByEmail(email);
        Optional<UserEntity> entityUsername = this.userRepository.findByUsername(username);
        if (entityEmail.isPresent() || entityUsername.isPresent()) {

            System.out.println("This user exists");
            return true;
        }
        return false;
    }


}

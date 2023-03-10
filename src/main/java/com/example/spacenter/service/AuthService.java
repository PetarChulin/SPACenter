package com.example.spacenter.service;

import com.example.spacenter.model.dto.LoginUserDTO;
import com.example.spacenter.model.dto.RegisterUserDTO;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.RoleRepository;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.session.LoggedUser;
import com.example.spacenter.model.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private LoggedUser session;
    private LoggedUser loggedUser;

    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;


    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser session, LoggedUser loggedUser, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.session = session;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public boolean register(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getRepeatPassword())) {
            return false;
        }

        Optional<UserEntity> email = this.userRepository.findByEmail(registerUserDTO.getEmail());
        Optional<UserEntity> username = this.userRepository.findByUsername(registerUserDTO.getUsername());
        Role userRole = this.roleRepository.findByName("USER");
        Role adminRole = this.roleRepository.findByName("ADMIN");

        if (email.isPresent() || username.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();

        user.setUsername(registerUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setFirstName(registerUserDTO.getFirstName());
        user.setLastName(registerUserDTO.getLastName());
        user.setEmail(registerUserDTO.getEmail());
        if (registerUserDTO.getUsername().equals("admin")) {
            user.addRole(adminRole);
        } else {
            user.addRole(userRole);
        }

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginUserDTO loginUserDTO) {


        Optional<UserEntity> confirmCredentials = this.userRepository
                .findByUsernameAndPassword(loginUserDTO.getUsername(),
                        loginUserDTO.getPassword());


        if (confirmCredentials.isEmpty()) {
            return false;
        }


        this.session.login(confirmCredentials.get());
        return true;
    }

    public boolean authenticate(String username, String password) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(password, userOptional.get().getPassword());
        }
    }


    public void logout() {
        this.session.logout();
    }


    public boolean isLoggedIn() {

        return this.session.getId() > 0;
    }
}

package com.example.spacenter.service;

import com.example.spacenter.model.dto.RegisterUserDTO;
import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.RoleRepository;
import com.example.spacenter.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthServiceTest {

    UserRepository userRepository = mock(UserRepository.class);
    RoleRepository roleRepository = mock(RoleRepository.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    RegisterUserDTO registerUserDTO = new RegisterUserDTO();
    AuthService authService = new AuthService(userRepository, passwordEncoder, roleRepository);

    @BeforeEach
    void setUp() {
        registerUserDTO.setUsername("peter");
        registerUserDTO.setPassword("password");
        registerUserDTO.setRepeatPassword("password");
        registerUserDTO.setRepeatPassword("differentPassword");

    }

    @Test
    public void testRegister_SuccessfulRegistration() {

        registerUserDTO.setUsername("peter");
        registerUserDTO.setPassword("password");
        registerUserDTO.setRepeatPassword("password");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName("USER")).thenReturn(mock(Role.class));
        when(roleRepository.findByName("ADMIN")).thenReturn(mock(Role.class));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");


        boolean result = authService.register(registerUserDTO);
        assertTrue(result);
    }

    @Test
    public void testRegister_FailedRegistration_PasswordMismatch() {

        AuthService authService = new AuthService(userRepository, passwordEncoder, roleRepository);

        boolean result = authService.register(registerUserDTO);
        assertFalse(result);
    }

    @Test
    public void testRegister_FailedRegistration_ExistingEmailOrUsername() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mock(UserEntity.class)));

        boolean result = authService.register(registerUserDTO);
        assertFalse(result);
    }


}

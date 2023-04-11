package com.example.spacenter.service;

import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.RoleRepository;
import com.example.spacenter.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class UserInitServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserInitService adminInitializationService;

    @Test
    public void initAdminTest() {
        // Given
        Role adminRole = new Role();
        adminRole.setId(1L);
        adminRole.setName("ADMIN");

        UserEntity admin = new UserEntity();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("123");
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.setEmail("admin@com.com");
        admin.addRole(adminRole);

        given(roleRepository.findByName("ADMIN")).willReturn(adminRole);
        given(passwordEncoder.encode("123")).willReturn("hashedPassword");
        given(userRepository.save(any(UserEntity.class))).willReturn(admin);

        // When
        adminInitializationService.initAdmin();

        // Then
        verify(roleRepository).findByName("ADMIN");
        verify(passwordEncoder).encode("123");
        verify(userRepository).save(argThat(user ->
                user.getUsername().equals("admin")
                        && user.getPassword().equals("hashedPassword")
                        && user.getFirstName().equals("Admin")
                        && user.getLastName().equals("Adminov")
                        && user.getEmail().equals("admin@com.com")
                        && user.getRoles().contains(adminRole)));
    }

    @Test
    public void initModeratorTest() {
        // Given
        Role moderatorRole = new Role();
        moderatorRole.setId(1L);
        moderatorRole.setName("ADMIN");

        UserEntity moderator = new UserEntity();
        moderator.setId(1L);
        moderator.setUsername("moderator");
        moderator.setPassword("123");
        moderator.setFirstName("Moderator");
        moderator.setLastName("Moderatorov");
        moderator.setEmail("moderator@com.com");
        moderator.addRole(moderatorRole);

        given(roleRepository.findByName("MODERATOR")).willReturn(moderatorRole);
        given(passwordEncoder.encode("123")).willReturn("hashedPassword");
        given(userRepository.save(any(UserEntity.class))).willReturn(moderator);

        // When
        adminInitializationService.initModerator();

        // Then
        verify(roleRepository).findByName("MODERATOR");
        verify(passwordEncoder).encode("123");
        verify(userRepository).save(argThat(user ->
                user.getUsername().equals("moderator")
                        && user.getPassword().equals("hashedPassword")
                        && user.getFirstName().equals("Moderator")
                        && user.getLastName().equals("Moderatorov")
                        && user.getEmail().equals("moderator@com.com")
                        && user.getRoles().contains(moderatorRole)));
    }

    @Test
    public void initUserTest() {

        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName("USER");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("user");
        userEntity.setPassword("123");
        userEntity.setFirstName("Petar");
        userEntity.setLastName("Petrov");
        userEntity.setEmail("user@com.com");
        userEntity.addRole(userRole);

        given(roleRepository.findByName("USER")).willReturn(userRole);
        given(passwordEncoder.encode("123")).willReturn("hashedPassword");
        given(userRepository.save(any(UserEntity.class))).willReturn(userEntity);

        // When
        adminInitializationService.initUser();

        // Then
        verify(roleRepository).findByName("USER");
        verify(passwordEncoder).encode("123");
        verify(userRepository).save(argThat(user ->
                user.getUsername().equals("user")
                        && user.getPassword().equals("hashedPassword")
                        && user.getFirstName().equals("Petar")
                        && user.getLastName().equals("Petrov")
                        && user.getEmail().equals("user@com.com")
                        && user.getRoles().contains(userRole)));
    }


}

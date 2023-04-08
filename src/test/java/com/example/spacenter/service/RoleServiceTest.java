package com.example.spacenter.service;


import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.RoleRepository;
import com.example.spacenter.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RoleServiceTest {



    @Test
    public void testAddRoleToUser_SuccessfulRoleAddition() {

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserDetailsService detailsService = Mockito.mock(UserDetailsService.class);
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
        RoleService service = new RoleService(userRepository, roleRepository, detailsService);

        String email = "test@example.com";
        String role = "ADMIN";

        UserEntity user = new UserEntity();
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = new AppUserDetails("testUser", "testPassword",
                List.of(new SimpleGrantedAuthority("ROLE_USER")), 12345L);
        Mockito.when(detailsService.loadUserByUsername(user.getEmail())).thenReturn(userDetails);

        Role roleEntity = new Role();
        Mockito.when(roleRepository.findByName(role)).thenReturn(roleEntity);

        service.addRoleToUser(email, role);

        assertTrue(user.getRoles().contains(roleEntity));
        Mockito.verify(userRepository).saveAndFlush(user);
    }
}


package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApplicationUserDetailsServiceTest {

    UserEntity userEntity = new UserEntity();
    UserRepository userRepository = mock(UserRepository.class);


    @BeforeEach

    void setUp() {
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPassword");
    }

    @Test
    public void testLoadUserByUsername_SuccessfulLoading() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        ApplicationUserDetailsService applicationUserDetailsService = new ApplicationUserDetailsService(userRepository);

        UserDetails userDetails = applicationUserDetailsService.loadUserByUsername("testUser");
        assertNotNull(userDetails);
    }

    @Test
    public void testLoadUserByUsername_FailedLoading_UserNotFound() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        ApplicationUserDetailsService applicationUserDetailsService = new ApplicationUserDetailsService(userRepository);

        assertThrows(UsernameNotFoundException.class, () -> applicationUserDetailsService.loadUserByUsername("nonExistingUser"));
    }

    @Test
    public void testAppUserDetails_SuccessfulCreation() {
        String username = "testUser";
        String password = "testPassword";
        Collection<? extends GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
        Long id = 12345L;

        AppUserDetails appUserDetails = new AppUserDetails(username, password, authorities, id);

        assertEquals(username, appUserDetails.getUsername());
        assertEquals(password, appUserDetails.getPassword());
        assertEquals(authorities, appUserDetails.getAuthorities());
        assertEquals(id, appUserDetails.getId());
    }

    @Test
    public void testAppUserDetails_InheritanceAndImplementation() {
        // Create an instance of the AppUserDetails class
        AppUserDetails appUserDetails = new AppUserDetails("testUser", "testPassword", List.of(new SimpleGrantedAuthority("ROLE_USER")), 12345L);

        // Assert that the object extends User and implements UserDetails
        assertTrue(appUserDetails instanceof User);
        assertTrue(appUserDetails instanceof UserDetails);
    }




}

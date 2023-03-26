package com.example.spacenter.services;

import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.RoleEnum;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.ApplicationUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private final String NOT_EXISTING_EMAIL = "pesho@example.com";

    private ApplicationUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(
                mockUserRepository
        );
    }

    @Test
    void testUserFound() {

        Role testAdminRole = new Role().setName(String.valueOf(RoleEnum.ADMIN));
        Role testUserRole = new Role().setName(String.valueOf(RoleEnum.USER));
        Role testModeratorRole = new Role().setName(String.valueOf(RoleEnum.MODERATOR));

        String EXISTING_EMAIL = "user@com.com";
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setEmail(EXISTING_EMAIL);
        testUserEntity.setPassword("123");
        testUserEntity.setRoles(Set.of(testAdminRole, testUserRole, testModeratorRole));


        when(mockUserRepository.findByEmail(EXISTING_EMAIL)).
                thenReturn(Optional.of(testUserEntity));

        UserDetails adminDetails =
                toTest.loadUserByUsername(EXISTING_EMAIL);

        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_EMAIL, adminDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(3,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just three - ADMIN/USER/MODERATOR.");

        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_USER");
        assertRole(adminDetails.getAuthorities(), "ROLE_MODERATOR");
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities, String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_EMAIL)
        );
    }

}

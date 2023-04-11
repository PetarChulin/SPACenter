package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.dto.BaseProcedureDTO;
import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import com.example.spacenter.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.Set;

import static com.example.spacenter.service.CommonService.addProcedure;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommonServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    SpaServicesRepository spaServicesRepository;

    @Mock
    CommonService commonService;

    @InjectMocks
    private ApplicationUserDetailsService service;


    @Test
    public void testSetProperties() {
        BaseProcedure baseProcedure = Mockito.mock(BaseProcedure.class);

        BaseProcedureDTO baseProcedureDTO = Mockito.mock(BaseProcedureDTO.class);

        when(baseProcedureDTO.getName()).thenReturn("Test Name");
        when(baseProcedureDTO.getImageUrl()).thenReturn("testImageUrl");
        when(baseProcedureDTO.getDescription()).thenReturn("Test Description");
        when(baseProcedureDTO.getPrice()).thenReturn(9.99);

        CommonService.setProperties(baseProcedureDTO, baseProcedure);

        Mockito.verify(baseProcedure).setId(Mockito.anyLong());
        Mockito.verify(baseProcedure).setType(Mockito.any());
        Mockito.verify(baseProcedure).setName("Test Name");
        Mockito.verify(baseProcedure).setImageUrl("testImageUrl");
        Mockito.verify(baseProcedure).setDescription("Test Description");
        Mockito.verify(baseProcedure).setPrice(9.99);
    }


    @Test
    void testGetUserId() {
        Authentication authentication = Mockito.mock(Authentication.class);
        AppUserDetails userDetails = new AppUserDetails("username", "password", authentication.getAuthorities(), 1L);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContextHolder.setContext(securityContext);

        Long userId = userDetails.getId();

        assertEquals(1L, userId);
    }

    @Test
    public void testLoadUserByUsername() {
        String username = "test@test.com";
        UserEntity user = new UserEntity(username, "password");
        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = service.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.getPassword().startsWith("password"));

    }

    @Test
    public void testAddProcedure() {
        BaseProcedure procedure = Mockito.mock(BaseProcedure.class);
        UserEntity user = Mockito.mock(UserEntity.class);

        UserEntity existingUser = Mockito.mock(UserEntity.class);
        Mockito.when(existingUser.getId()).thenReturn(1L);

        Mockito.when(procedure.getBuyers()).thenReturn(Set.of(existingUser));

        addProcedure(procedure, user);

        Mockito.verify(procedure, Mockito.times(1)).addBuyer(user);
    }

    @Test
    void testDeleteProcedure() {

        SpaServices spaService = new SpaServices();
        spaService.setId(1L);
        spaService.setName("Sample SpaService");
        spaService.setPrice(10.0);
        spaServicesRepository.save(spaService);

        commonService.deleteProcedure(1L);

        assertFalse(spaServicesRepository.findById(1L).isPresent());
    }

}



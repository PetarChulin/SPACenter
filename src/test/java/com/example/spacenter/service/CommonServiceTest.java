package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.dto.BaseProcedureDTO;
import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommonServiceTest {

    @Test
    public void testSetProperties() {
        BaseProcedure baseProcedure = Mockito.mock(BaseProcedure.class);

        BaseProcedureDTO baseProcedureDTO = Mockito.mock(BaseProcedureDTO.class);

        Mockito.when(baseProcedureDTO.getName()).thenReturn("Test Name");
        Mockito.when(baseProcedureDTO.getImageUrl()).thenReturn("testImageUrl");
        Mockito.when(baseProcedureDTO.getDescription()).thenReturn("Test Description");
        Mockito.when(baseProcedureDTO.getPrice()).thenReturn(9.99);

        CommonService.setProperties(baseProcedureDTO, baseProcedure);

        Mockito.verify(baseProcedure).setId(Mockito.anyLong());
        Mockito.verify(baseProcedure).setType(Mockito.any());
        Mockito.verify(baseProcedure).setName("Test Name");
        Mockito.verify(baseProcedure).setImageUrl("testImageUrl");
        Mockito.verify(baseProcedure).setDescription("Test Description");
        Mockito.verify(baseProcedure).setPrice(9.99);
    }

}

package com.example.spacenter.service;

import com.example.spacenter.model.dto.MedicalProceduresDTO;
import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MedicalProcedureServiceTest {
    MedicalProceduresDTO medicalProceduresDTO = new MedicalProceduresDTO();


    @BeforeEach
    void setUp() {
        medicalProceduresDTO.setName("testProcedure");
        medicalProceduresDTO.setDescription("Test description");
        medicalProceduresDTO.setImageUrl("test-image-url");
        medicalProceduresDTO.setName("existingProcedure");

    }

    @Test
    public void testAdd_SuccessfulAddition() {


        MedicalProceduresRepository medicalProceduresRepository = mock(MedicalProceduresRepository.class);
        when(medicalProceduresRepository.findByName(anyString())).thenReturn(Optional.empty());

        MedicalProcedureService medicalProcedureService = new MedicalProcedureService(medicalProceduresRepository);
        boolean result = medicalProcedureService.add(medicalProceduresDTO);
        assertTrue(result);

        verify(medicalProceduresRepository).save(any(MedicalProcedure.class));
    }

    @Test
    public void testAdd_FailedAddition_DuplicateProcedureName() {


        MedicalProceduresRepository medicalProceduresRepository = mock(MedicalProceduresRepository.class);
        when(medicalProceduresRepository.findByName(anyString())).thenReturn(Optional.of(mock(MedicalProcedure.class)));

        MedicalProcedureService medicalProcedureService = new MedicalProcedureService(medicalProceduresRepository);

        boolean result = medicalProcedureService.add(medicalProceduresDTO);
        assertFalse(result);
    }


}

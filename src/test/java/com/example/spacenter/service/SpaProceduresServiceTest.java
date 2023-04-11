package com.example.spacenter.service;

import com.example.spacenter.model.dto.SpaProceduresDTO;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.repositories.SpaProceduresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SpaProceduresServiceTest {

    SpaProceduresDTO spaProceduresDTO = new SpaProceduresDTO();
    SpaProceduresRepository spaProceduresRepository = mock(SpaProceduresRepository.class);
    SpaProcedureService spaProcedureService = new SpaProcedureService(spaProceduresRepository);

    @BeforeEach
    void setUp() {
        spaProceduresDTO.setName("testProcedure");
        spaProceduresDTO.setDescription("Test description");
        spaProceduresDTO.setImageUrl("test-image-url");
        spaProceduresDTO.setName("existingProcedure");

    }

    @Test
    public void testAdd_SuccessfulAddition() {

        when(spaProceduresRepository.findByName(anyString())).thenReturn(Optional.empty());

        boolean result = spaProcedureService.add(spaProceduresDTO);
        assertTrue(result);

        verify(spaProceduresRepository).save(any(SpaProcedure.class));
    }

    @Test
    public void testAdd_FailedAddition_DuplicateProcedureName() {

        when(spaProceduresRepository.findByName(anyString())).thenReturn(Optional.of(mock(SpaProcedure.class)));
        boolean result = spaProcedureService.add(spaProceduresDTO);
        assertFalse(result);
    }
}

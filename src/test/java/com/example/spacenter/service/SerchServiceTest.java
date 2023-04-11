package com.example.spacenter.service;

import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest

public class SerchServiceTest {

    private final SapropelRepository sapropelRepository = mock(SapropelRepository.class);
    private final LaserRepository laserRepository = mock(LaserRepository.class);
    private final SpaRitualsRepository spaRitualsRepository = mock(SpaRitualsRepository.class);
    private final SpaServicesRepository spaServicesRepository = mock(SpaServicesRepository.class);


    private final SearchService searchService = new SearchService(
            sapropelRepository,
            laserRepository,
            spaRitualsRepository,
            spaServicesRepository
    );


    @Test
    void findByKeyword_WithNoMatchingKeywords_ReturnsEmptyList() {
        // Arrange
        when(sapropelRepository.findByKeyword("mask")).thenReturn(Arrays.asList());
        when(laserRepository.findByKeyword("facial")).thenReturn(Arrays.asList());
        when(spaRitualsRepository.findByKeyword("rituals")).thenReturn(Arrays.asList());
        when(spaServicesRepository.findByKeyword("hydrotherapy")).thenReturn(Arrays.asList());

        // Act
        List<Object> procedures = searchService.findByKeyword("mask");

        // Assert
        Assertions.assertTrue(procedures.isEmpty());
        verify(sapropelRepository, times(1)).findByKeyword("mask");
        verify(laserRepository, times(1)).findByKeyword("mask");
        verify(spaRitualsRepository, times(1)).findByKeyword("mask");
        verify(spaServicesRepository, times(1)).findByKeyword("mask");
    }

}

package com.example.spacenter.service;

import com.example.spacenter.model.dto.SpaProcedureDTO.SpaRitualsDTO;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import com.example.spacenter.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class SpaSubProceduresTest {
    @Mock
    SpaRitualsRepository spaRitualsRepository;

    UserRepository userRepository;

    SpaServicesRepository spaServicesRepository;


    @Test
    void addSpaRituals_shouldReturnTrue_whenNewSpaRitualsAdded() {

        SpaRitualsDTO dto = new SpaRitualsDTO();
        dto.setName("Test Spa Rituals");
        dto.setImageUrl("/images/test-spa-rituals.jpg");
        dto.setDescription("Test description");
        dto.setPrice(100.0);

        SpaRituals spaRituals = new SpaRituals();
        spaRituals.setId(1L);
        spaRituals.setName(dto.getName());
        spaRituals.setImageUrl(dto.getImageUrl());
        spaRituals.setDescription(dto.getDescription());
        spaRituals.setPrice(dto.getPrice());

        Mockito.when(spaRitualsRepository.findByName(dto.getName())).thenReturn(Optional.empty());
        Mockito.when(spaRitualsRepository.save(Mockito.any(SpaRituals.class))).thenReturn(spaRituals);

        SpaSubProceduresService service = new SpaSubProceduresService(spaRitualsRepository,
                userRepository,
                spaServicesRepository);

        boolean result = service.addSpaRituals(dto);

        assertTrue(result);
        Mockito.verify(spaRitualsRepository, Mockito.times(1)).findByName(dto.getName());
        Mockito.verify(spaRitualsRepository, Mockito.times(1)).save(Mockito.any(SpaRituals.class));
    }
}

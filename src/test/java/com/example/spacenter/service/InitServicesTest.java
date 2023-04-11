package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InitServicesTest {
    LaserProcedure expectedLaser = new LaserProcedure();
    SapropelProcedure expectedSapropel = new SapropelProcedure();

    SpaRituals expectedSpaRituals = new SpaRituals();

    SpaServices expectedSpaServices = new SpaServices();
    @Test
    public void testInitLaserProcedure() {

        LaserRepository laserRepositoryMock = mock(LaserRepository.class);
        Optional<LaserProcedure> entityName = Optional.empty();
        when(laserRepositoryMock.findByName("Carboxytherapy – facial treatment")).thenReturn(entityName);

        LaserProceduresInitService yourService = new LaserProceduresInitService(laserRepositoryMock);

        yourService.initLaserProcedure();
        expectedLaser.setName("Carboxytherapy – facial treatment");

        ArgumentCaptor<LaserProcedure> argumentCaptor = ArgumentCaptor.forClass(LaserProcedure.class);
        verify(laserRepositoryMock).save(argumentCaptor.capture());
        LaserProcedure actualLaser = argumentCaptor.getValue();

        assertEquals(expectedLaser.getName(), actualLaser.getName());
    }



    @Test
    public void testInitSapropelProcedure() {

        SapropelRepository repositoryMock = mock(SapropelRepository.class);
        Optional<SapropelProcedure> entityName = Optional.empty();
        when(repositoryMock.findByName("Paraffin bath for feet")).thenReturn(entityName);

        SapropelProceduresInitService service = new SapropelProceduresInitService(repositoryMock);

        service.initSapropelProcedure1();
        expectedSapropel.setName("Paraffin bath for feet");

        ArgumentCaptor<SapropelProcedure> argumentCaptor = ArgumentCaptor.forClass(SapropelProcedure.class);
        verify(repositoryMock).save(argumentCaptor.capture());
        SapropelProcedure actualLaser = argumentCaptor.getValue();

        assertEquals(expectedSapropel.getName(), actualLaser.getName());
    }

    @Test
    public void testInitSpaRituals() {

        SpaRitualsRepository repositoryMock = mock(SpaRitualsRepository.class);
        Optional<SpaRituals> entityName = Optional.empty();
        when(repositoryMock.findByName("Amber pint massage")).thenReturn(entityName);

        SpaRitualsInitService service = new SpaRitualsInitService(repositoryMock);

        service.initSpaRitual1();
        expectedSpaRituals.setName("Amber pint massage");

        ArgumentCaptor<SpaRituals> argumentCaptor = ArgumentCaptor.forClass(SpaRituals.class);
        verify(repositoryMock).save(argumentCaptor.capture());
        SpaRituals actualLaser = argumentCaptor.getValue();

        assertEquals(expectedSpaRituals.getName(), actualLaser.getName());
    }


    @Test
    public void testInitSpaServices() {

        SpaServicesRepository repositoryMock = mock(SpaServicesRepository.class);
        Optional<SpaServices> entityName = Optional.empty();
        when(repositoryMock.findByName("Classic hammam procedure")).thenReturn(entityName);

        SpaServicesInitService service = new SpaServicesInitService(repositoryMock);

        service.initSpaService1();
        expectedSpaServices.setName("Classic hammam procedure");

        ArgumentCaptor<SpaServices> argumentCaptor = ArgumentCaptor.forClass(SpaServices.class);
        verify(repositoryMock).save(argumentCaptor.capture());
        SpaServices actualLaser = argumentCaptor.getValue();

        assertEquals(expectedSpaServices.getName(), actualLaser.getName());
    }
}

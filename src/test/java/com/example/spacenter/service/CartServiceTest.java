package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import com.example.spacenter.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CartServiceTest {

    @Test
    public void testDeleteAllFromUserCart_SuccessfulDeletion() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        SapropelRepository sapropelRepository = Mockito.mock(SapropelRepository.class);
        LaserRepository laserRepository = Mockito.mock(LaserRepository.class);
        SpaRitualsRepository spaRitualsRepository = Mockito.mock(SpaRitualsRepository.class);
        SpaServicesRepository spaServicesRepository = Mockito.mock(SpaServicesRepository.class);
        MedicalSubProceduresService medicalSubProceduresService = Mockito.mock(MedicalSubProceduresService.class);

        CartService service = new CartService(sapropelRepository,userRepository, medicalSubProceduresService, laserRepository, spaRitualsRepository, spaServicesRepository);

        Long userId = 12345L;

        UserEntity user = new UserEntity();
        Mockito.when(userRepository.getUsersById(userId)).thenReturn(user);

        List<SapropelProcedure> sapropelOrders = Arrays.asList(new SapropelProcedure(), new SapropelProcedure());
        Mockito.when(sapropelRepository.findByBuyers_Id(userId)).thenReturn(sapropelOrders);

        List<LaserProcedure> laserOrders = Arrays.asList(new LaserProcedure(), new LaserProcedure());
        Mockito.when(laserRepository.findByBuyers_Id(userId)).thenReturn(laserOrders);

        List<SpaRituals> spaRitualsOrders = Arrays.asList(new SpaRituals(), new SpaRituals());
        Mockito.when(spaRitualsRepository.findByBuyers_Id(userId)).thenReturn(spaRitualsOrders);

        List<SpaServices> spaServicesOrders = Arrays.asList(new SpaServices(), new SpaServices());
        Mockito.when(spaServicesRepository.findByBuyers_Id(userId)).thenReturn(spaServicesOrders);

        service.deleteAllFromUserCart();

        Mockito.verify(sapropelRepository).deleteAll(sapropelOrders);
        Mockito.verify(laserRepository).deleteAll(laserOrders);
        Mockito.verify(spaRitualsRepository).deleteAll(spaRitualsOrders);
        Mockito.verify(spaServicesRepository).deleteAll(spaServicesOrders);
    }

}

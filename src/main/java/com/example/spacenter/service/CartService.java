package com.example.spacenter.service;

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
import com.example.spacenter.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.spacenter.service.CommonService.getUserId;

@Service
public class CartService {

    private SapropelRepository sapropelRepository;

    private UserRepository userRepository;

    private MedicalSubProceduresService medicalSubProceduresService;
    private LaserRepository laserRepository;

    private SpaRitualsRepository spaRitualsRepository;

    private SpaServicesRepository spaServicesRepository;

    public CartService(SapropelRepository sapropelRepository, UserRepository userRepository, MedicalSubProceduresService medicalSubProceduresService, LaserRepository laserRepository, SpaRitualsRepository spaRitualsRepository, SpaServicesRepository spaServicesRepository) {
        this.sapropelRepository = sapropelRepository;
        this.userRepository = userRepository;
        this.medicalSubProceduresService = medicalSubProceduresService;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
        this.spaServicesRepository = spaServicesRepository;
    }

    @Transactional
    public void deleteAllFromUserCart() {

        Long id = getUserId();

        UserEntity user = this.userRepository.getUsersById(id);

        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(id);
        List<LaserProcedure> laserOrders = this.laserRepository.findByBuyers_Id(id);
        List<SpaRituals> spaRitualOrders = this.spaRitualsRepository.findByBuyers_Id(id);
        List<SpaServices> spaServicesOrders = this.spaServicesRepository.findByBuyers_Id(id);

        allOrders(sapropelOrders, laserOrders, spaRitualOrders, spaServicesOrders, user);

    }

    public static void allOrders(List<SapropelProcedure> sapropelOrders, List<LaserProcedure> laserOrders,
                                 List<SpaRituals> spaOrders, List<SpaServices> spaServiceOrders, UserEntity user) {
        List<BaseProcedure> allOrders = new ArrayList<>();
        allOrders.addAll(sapropelOrders);
        allOrders.addAll(laserOrders);
        allOrders.addAll(spaOrders);
        allOrders.addAll(spaServiceOrders);

        for (BaseProcedure allOrder : allOrders) {
            allOrder.removeBuyer(user);
        }
    }


}

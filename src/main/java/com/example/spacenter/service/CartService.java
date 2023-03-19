package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.session.LoggedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.spacenter.service.MedicalSubProceduresService.getUserId;

@Service
public class CartService {

    private LoggedUser loggedUser;
    private SapropelRepository sapropelRepository;

    private UserRepository userRepository;

    private MedicalSubProceduresService medicalSubProceduresService;
    private LaserRepository laserRepository;

    public CartService(LoggedUser loggedUser, SapropelRepository sapropelRepository, UserRepository userRepository, MedicalSubProceduresService medicalSubProceduresService, LaserRepository laserRepository) {
        this.loggedUser = loggedUser;
        this.sapropelRepository = sapropelRepository;
        this.userRepository = userRepository;
        this.medicalSubProceduresService = medicalSubProceduresService;
        this.laserRepository = laserRepository;
    }


    //    @Transient
//    public Double getTotalOrderPrice(Long id) {
//        double sum = 0D;
//    List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(id);
//        for (SapropelProcedure op : sapropelOrders) {
//            sum += op.getPrice();
//        }
//        return sum;
//    }
    @Transactional
    public void deleteAllFromUserCart() {

        Long id = getUserId();

        UserEntity user = this.userRepository.getUsersById(id);

        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(id);


        for (SapropelProcedure sapropelOrder : sapropelOrders) {
            sapropelOrder.removeBuyer(user);
        }
//
//        List<LaserProcedure> laserOrders = this.laserRepository.findByBuyers_Id(userId);
//
//        for (LaserProcedure laserOrder : laserOrders) {
//            laserOrder.removeBuyer(user);
//        }


    }
}

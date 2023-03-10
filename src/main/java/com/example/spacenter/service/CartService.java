package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private LoggedUser loggedUser;
    private SapropelRepository sapropelRepository;

    private UserRepository userRepository;

    public CartService(LoggedUser loggedUser, SapropelRepository sapropelRepository, UserRepository userRepository) {
        this.loggedUser = loggedUser;
        this.sapropelRepository = sapropelRepository;
        this.userRepository = userRepository;
    }



    @Transient
    public Double getTotalOrderPrice(Long id) {
        double sum = 0D;
    List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(id);
        for (SapropelProcedure op : sapropelOrders) {
            sum += op.getPrice();
        }
        return sum;
    }
}

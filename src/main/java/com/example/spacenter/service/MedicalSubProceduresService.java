package com.example.spacenter.service;

import com.example.spacenter.model.dto.MedicalProcedureDTO.LaserProceduresDTO;
import com.example.spacenter.model.dto.MedicalProcedureDTO.SapropelProceduresDTO;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.spacenter.service.CommonService.addProcedure;
import static com.example.spacenter.service.CommonService.getUserEntity;

@Service
public class MedicalSubProceduresService {

    private SapropelRepository sapropelRepository;

    private LaserRepository laserRepository;
    private UserRepository userRepository;

    @Autowired
    public MedicalSubProceduresService(SapropelRepository sapropelRepository, LaserRepository laserRepository, UserRepository userRepository) {
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.userRepository = userRepository;
    }


    public boolean addSapropelProcedure(SapropelProceduresDTO sapropelProceduresDTO) {

        SapropelProcedure sapropelProcedure = new SapropelProcedure();

        Optional<SapropelProcedure> findByName = this.sapropelRepository.findByName(sapropelProceduresDTO.getName());
        if (findByName.isPresent()) {
            return true;
        }
        sapropelProcedure.setType(sapropelProcedure.getType());
        sapropelProcedure.setName(sapropelProceduresDTO.getName());
        sapropelProcedure.setImageUrl(sapropelProceduresDTO.getImageUrl());
        sapropelProcedure.setDescription(sapropelProceduresDTO.getDescription());
        sapropelProcedure.setPrice(sapropelProceduresDTO.getPrice());

        this.sapropelRepository.save(sapropelProcedure);

        return true;
    }


    @Transactional
    public void addSapropelToCart(Long id) {

        SapropelProcedure sapropelProcedure = this.sapropelRepository.findById(id).get();
        UserEntity user = getUserEntity();
        addProcedure(sapropelProcedure, user);
    }

    public Page<SapropelProcedure> getAllSapropel(Pageable pageable) {
        return sapropelRepository.findAll(pageable);
    }

    public Page<LaserProcedure> getAllLaser(Pageable pageable) {
        return laserRepository.findAll(pageable);
    }


    @Transactional
    public void deleteSapropelFromCart(Long id) {

        SapropelProcedure sapropelProcedure = this.sapropelRepository.findById(id).get();
        UserEntity user = getUserEntity();
        sapropelProcedure.removeBuyer(user);
    }

    public boolean addLaserProcedure(LaserProceduresDTO laserProceduresDTO) {

        LaserProcedure laserProcedure = new LaserProcedure();

        Optional<LaserProcedure> findByName = this.laserRepository.findByName(laserProceduresDTO.getName());
        if (findByName.isPresent()) {
            return false;
        }
        laserProcedure.setType(laserProcedure.getType());
        laserProcedure.setName(laserProceduresDTO.getName());
        laserProcedure.setImageUrl(laserProceduresDTO.getImageUrl());
        laserProcedure.setPrice(laserProceduresDTO.getPrice());
        laserProcedure.setDescription(laserProceduresDTO.getDescription());

        this.laserRepository.save(laserProcedure);

        return true;

    }

    @Transactional
    public void addLaserToCart(Long id) {

        LaserProcedure laserProcedure = this.laserRepository.findById(id).get();
        UserEntity user = getUserEntity();
        addProcedure(laserProcedure, user);

    }

    @Transactional
    public void deleteLaserFromCart(Long id) {

        LaserProcedure laserProcedure = this.laserRepository.findById(id).get();
        UserEntity user = getUserEntity();
        laserProcedure.removeBuyer(user);
    }




}

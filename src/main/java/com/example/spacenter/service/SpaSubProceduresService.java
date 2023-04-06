package com.example.spacenter.service;

import com.example.spacenter.model.dto.SpaProcedureDTO.SpaRitualsDTO;
import com.example.spacenter.model.dto.SpaProcedureDTO.SpaServicesDTO;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.spacenter.service.CommonService.addProcedure;
import static com.example.spacenter.service.CommonService.getUserId;

@Service
public class SpaSubProceduresService {

    private SpaRitualsRepository spaRitualsRepository;

    private UserRepository userRepository;

    private SpaServicesRepository spaServicesRepository;


    public SpaSubProceduresService(SpaRitualsRepository spaRitualsRepository, UserRepository userRepository, SpaServicesRepository spaServicesRepository) {
        this.spaRitualsRepository = spaRitualsRepository;
        this.userRepository = userRepository;
        this.spaServicesRepository = spaServicesRepository;
    }

    public boolean addSpaRituals(SpaRitualsDTO spaRitualsDTO) {

        SpaRituals spaRituals = new SpaRituals();

        Optional<SpaRituals> findByName = this.spaRitualsRepository.findByName(spaRitualsDTO.getName());
        if (findByName.isPresent()) {
            return false;
        }

        spaRituals.setId((long) Math.floor(Math.random() * 10));
        spaRituals.setType(spaRituals.getType());
        spaRituals.setName(spaRitualsDTO.getName());
        spaRituals.setImageUrl(spaRitualsDTO.getImageUrl());
        spaRituals.setDescription(spaRitualsDTO.getDescription());
        spaRituals.setPrice(spaRitualsDTO.getPrice());

        this.spaRitualsRepository.save(spaRituals);

        return true;
    }

    public boolean addSpaServices(SpaServicesDTO spaServicesDTO) {

        SpaServices spaServices = new SpaServices();

        Optional<SpaServices> findByName = this.spaServicesRepository.findByName(spaServicesDTO.getName());
        if (findByName.isPresent()) {
            return false;
        }

        spaServices.setId((long) Math.floor(Math.random() * 20));
        spaServices.setType(spaServices.getType());
        spaServices.setName(spaServicesDTO.getName());
        spaServices.setImageUrl(spaServicesDTO.getImageUrl());
        spaServices.setDescription(spaServicesDTO.getDescription());
        spaServices.setPrice(spaServicesDTO.getPrice());

        this.spaServicesRepository.save(spaServices);

        return true;
    }

    public Page<SpaRituals> getAllSpaRituals(Pageable pageable) {
        return spaRitualsRepository.findAll(pageable);
    }
    public Page<SpaServices> getAllSpaServices(Pageable pageable) {
        return spaServicesRepository.findAll(pageable);
    }

    @Transactional
    public void addSpaRitualToCart(Long id) {

        SpaRituals spaRituals = this.spaRitualsRepository.findById(id).get();
        Long userId = getUserId();
        UserEntity user = this.userRepository.getUsersById(userId);
        addProcedure(spaRituals, user);

    }

    @Transactional
    public void deleteSpaRitualFromCart(Long id) {

        SpaRituals spaRituals = this.spaRitualsRepository.findById(id).get();
        Long userId = getUserId();
        UserEntity user = this.userRepository.getUsersById(userId);
        spaRituals.removeBuyer(user);
    }

    @Transactional
    public void addSpaServiceToCart(Long id) {

        SpaServices spaServices = this.spaServicesRepository.findById(id).get();
        Long userId = getUserId();
        UserEntity user = this.userRepository.getUsersById(userId);
        addProcedure(spaServices, user);

    }
    @Transactional
    public void deleteSpaServiceFromCart(Long id) {

        SpaServices spaServices = this.spaServicesRepository.findById(id).get();
        Long userId = getUserId();
        UserEntity user = this.userRepository.getUsersById(userId);
        spaServices.removeBuyer(user);
    }
}

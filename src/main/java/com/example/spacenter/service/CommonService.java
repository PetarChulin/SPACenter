package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommonService {

    private static UserRepository userRepository;
    public static boolean inCart = false;

    private SapropelRepository sapropelRepository;

    private LaserRepository laserRepository;

    private SpaRitualsRepository spaRitualsRepository;


    public CommonService(UserRepository userRepository, SapropelRepository sapropelRepository, LaserRepository laserRepository, SpaRitualsRepository spaRitualsRepository) {
        CommonService.userRepository = userRepository;
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
    }

    public static UserEntity getUserEntity() {
        Long userId = getUserId();

        return userRepository.getUsersById(userId);
    }

    public static Long getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

    static void addProcedure(BaseProcedure procedure, UserEntity user) {
        UserEntity existingUser = procedure.getBuyers()
                .stream()
                .filter(found -> user.getId().equals(found.getId()))
                .findFirst().orElse(new UserEntity());

        if (procedure.getBuyers().contains(existingUser)) {
            inCart = true;
        } else {
            inCart = false;
            procedure.addBuyer(user);
        }
    }

    public void getAllProcedures() {
        List<SapropelProcedure> sapropelProcedures = sapropelRepository.findAll();
        List<LaserProcedure> laserProcedures = laserRepository.findAll();
        List<SpaRituals> spaProcedures = spaRitualsRepository.findAll();

        List<Object> allProcedures = new ArrayList<>();
        allProcedures.addAll(sapropelProcedures);
        allProcedures.addAll(laserProcedures);
        allProcedures.addAll(spaProcedures);

    }
}

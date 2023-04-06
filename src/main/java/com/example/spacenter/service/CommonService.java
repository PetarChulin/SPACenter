package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class CommonService {

    private static UserRepository userRepository;
    public static boolean inCart = false;

    private static SapropelRepository sapropelRepository;

    private static LaserRepository laserRepository;

    private static SpaRitualsRepository spaRitualsRepository;

    private static SpaServicesRepository spaServicesRepository;


    public CommonService(UserRepository userRepository, SapropelRepository sapropelRepository,
                         LaserRepository laserRepository, SpaRitualsRepository spaRitualsRepository,
                         SpaServicesRepository spaServicesRepository) {
        CommonService.userRepository = userRepository;
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
        this.spaServicesRepository = spaServicesRepository;
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

    public static List<BaseProcedure> getAllProcedures() {
        List<SapropelProcedure> sapropelProcedures = sapropelRepository.findAll();
        List<LaserProcedure> laserProcedures = laserRepository.findAll();
        List<SpaRituals> spaProcedures = spaRitualsRepository.findAll();
        List<SpaServices> spaServices = spaServicesRepository.findAll();

        List<BaseProcedure> allProcedures = new ArrayList<>();
        allProcedures.addAll(sapropelProcedures);
        allProcedures.addAll(laserProcedures);
        allProcedures.addAll(spaProcedures);
        allProcedures.addAll(spaServices);

        return allProcedures;
    }

    public void deleteProcedure(Long id) {

        List<BaseProcedure> all = getAllProcedures();

        BaseProcedure procedureForDeletion = all.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findAny().orElseThrow();

        if (procedureForDeletion instanceof SpaServices) {
            spaServicesRepository.deleteById(id);
        } else if (procedureForDeletion instanceof SpaRituals) {
            spaRitualsRepository.deleteById(id);
        } else if (procedureForDeletion instanceof LaserProcedure) {
            laserRepository.deleteById(id);
        } else if (procedureForDeletion instanceof SapropelProcedure) {
            sapropelRepository.deleteById(id);
        }
        all.remove(procedureForDeletion);
    }
}

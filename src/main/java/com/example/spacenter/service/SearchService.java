package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private SapropelRepository sapropelRepository;

    private LaserRepository laserRepository;

    private SpaRitualsRepository spaRitualsRepository;


    public SearchService(SapropelRepository sapropelRepository, LaserRepository laserRepository,
                         SpaRitualsRepository spaRitualsRepository) {
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
    }


    public List<Object> findByKeyword(String keyword) {

        List<SapropelProcedure> sapropelProcedures = sapropelRepository.findByKeyword(keyword);
        List<LaserProcedure> laserProcedures = laserRepository.findByKeyword(keyword);
        List<SpaRituals> spaProcedures = spaRitualsRepository.findByKeyword(keyword);


        List<Object> allProcedures = new ArrayList<>();
        allProcedures.addAll(sapropelProcedures);
        allProcedures.addAll(laserProcedures);
        allProcedures.addAll(spaProcedures);

        return allProcedures;
    }



}

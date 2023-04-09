package com.example.spacenter.service;

import com.example.spacenter.model.dto.MedicalProceduresDTO;
import com.example.spacenter.model.dto.SpaProceduresDTO;
import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaProceduresRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaProcedureService {

    private SpaProceduresRepository spaProceduresRepository;

    public SpaProcedureService(SpaProceduresRepository spaProceduresRepository) {
        this.spaProceduresRepository = spaProceduresRepository;
    }

    public SpaProcedureService(SpaServicesRepository spaServicesRepository, SpaRitualsRepository spaRitualsRepository, LaserRepository laserRepository, SapropelRepository sapropelRepository, MedicalProceduresRepository medicalProceduresRepository, ProceduresInitService proceduresInitService) {
    }

    public boolean add(SpaProceduresDTO spaProceduresDTO) {


        Optional<SpaProcedure> findByName = this.spaProceduresRepository.findByName(spaProceduresDTO.getName());
        if (findByName.isPresent()) {
            return false;
        }

        SpaProcedure spaProcedure = new SpaProcedure();

        spaProcedure.setId((long) Math.floor(Math.random() * 10));
        spaProcedure.setName(spaProceduresDTO.getName());
        spaProcedure.setDescription(spaProceduresDTO.getDescription());
        spaProcedure.setImageUrl(spaProceduresDTO.getImageUrl());

        spaProceduresRepository.save(spaProcedure);

        return true;
    }
}

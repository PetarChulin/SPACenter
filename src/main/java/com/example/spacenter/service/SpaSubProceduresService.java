package com.example.spacenter.service;

import com.example.spacenter.model.dto.SpaProcedureDTO.SpaRitualsDTO;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaSubProceduresService {

    private SpaRitualsRepository spaRitualsRepository;

    private UserRepository userRepository;


    public SpaSubProceduresService(SpaRitualsRepository spaRitualsRepository, UserRepository userRepository) {
        this.spaRitualsRepository = spaRitualsRepository;
        this.userRepository = userRepository;
    }

    public boolean addSpaRituals(SpaRitualsDTO spaRitualsDTO) {

        SpaRituals spaRituals = new SpaRituals();

        Optional<SpaRituals> findByName = this.spaRitualsRepository.findByName(spaRitualsDTO.getName());
        if (findByName.isPresent()) {
            return false;
        }

        spaRituals.setType(spaRituals.getType());
        spaRituals.setName(spaRitualsDTO.getName());
        spaRituals.setImageUrl(spaRitualsDTO.getImageUrl());
        spaRituals.setDescription(spaRitualsDTO.getDescription());
        spaRituals.setPrice(spaRitualsDTO.getPrice());

        return true;
    }

    public Page<SpaRituals> getAllSpaRituals(Pageable pageable) {
        return spaRitualsRepository.findAll(pageable);
    }
}

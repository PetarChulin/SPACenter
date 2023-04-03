package com.example.spacenter.service;


import com.example.spacenter.model.dto.MedicalProceduresDTO;
import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalProcedureService {

    private MedicalProceduresRepository medicalProceduresRepository;


    public MedicalProcedureService(MedicalProceduresRepository medicalProceduresRepository) {
        this.medicalProceduresRepository = medicalProceduresRepository;
    }


    public boolean add(MedicalProceduresDTO medicalProceduresDTO) {

        Optional<MedicalProcedure> findByName = this.medicalProceduresRepository.findByName(medicalProceduresDTO.getName());
        if (findByName.isPresent()) {
            return false;
        }

        MedicalProcedure medicalProcedure = new MedicalProcedure();

        medicalProcedure.setId((long) Math.floor(Math.random() * 10));
        medicalProcedure.setName(medicalProceduresDTO.getName());
        medicalProcedure.setDescription(medicalProceduresDTO.getDescription());
        medicalProcedure.setImageUrl(medicalProceduresDTO.getImageUrl());

        this.medicalProceduresRepository.save(medicalProcedure);

        return true;
    }
}

package com.example.spacenter.repositories.MedicalSubProceduresRepos;

import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SapropelRepository extends JpaRepository<SapropelProcedure, Long> {


    Optional<SapropelProcedure> findByName(String name);

    Optional<SapropelProcedure> findById(Long id);


    List<SapropelProcedure> findAll();

    List<SapropelProcedure> findByBuyers_Id(Long id);


}

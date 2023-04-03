package com.example.spacenter.repositories.MedicalSubProceduresRepos;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaserRepository extends JpaRepository<LaserProcedure, Long> {

    Optional<LaserProcedure> findByName(String name);

    Optional<LaserProcedure> findById(Long id);

    LaserProcedure getById(Long id);


    List<LaserProcedure> findAll();

    List<LaserProcedure> findByBuyers_Id(Long id);
}

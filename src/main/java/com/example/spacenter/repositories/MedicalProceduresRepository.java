package com.example.spacenter.repositories;

import com.example.spacenter.model.entity.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalProceduresRepository extends JpaRepository<MedicalProcedure, Long> {

    Optional<MedicalProcedure> findByName(String name);


}

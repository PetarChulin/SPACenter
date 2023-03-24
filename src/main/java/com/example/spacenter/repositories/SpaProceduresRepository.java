package com.example.spacenter.repositories;

import com.example.spacenter.model.entity.SpaProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpaProceduresRepository extends JpaRepository<SpaProcedure, Long> {

    Optional<SpaProcedure> findByName(String name);

}

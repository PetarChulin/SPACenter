package com.example.spacenter.repositories;

import com.example.spacenter.model.entity.SpaProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaProceduresRepository extends JpaRepository<SpaProcedure, Long> {

}

package com.example.spacenter.repositories.SpaSubProceduresRepos;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpaRitualsRepository extends JpaRepository<SpaRituals, Long> {

    Optional<SpaRituals> findByName(String name);

    List<SpaRituals> findByBuyers_Id(Long userId);

    @Query("SELECT p FROM SpaRituals p WHERE p.name LIKE %?1%")
    List<SpaRituals> findByKeyword(String keyword);
}

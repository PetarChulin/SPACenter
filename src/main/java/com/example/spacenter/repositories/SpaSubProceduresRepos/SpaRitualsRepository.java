package com.example.spacenter.repositories.SpaSubProceduresRepos;

import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaRitualsRepository extends JpaRepository<SpaRituals, Long> {

    Optional<SpaRituals> findByName(String name);

}

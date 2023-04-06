package com.example.spacenter.repositories.SpaSubProceduresRepos;

import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaServicesRepository extends JpaRepository<SpaServices, Long> {

    Optional<SpaServices> findByName(String name);

    List<SpaServices> findByBuyers_Id(Long userId);

    @Query("SELECT p FROM SpaServices p WHERE p.name LIKE %?1%")
    List<SpaServices> findByKeyword(String keyword);
}

package com.example.spacenter.repositories;

import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findAll();

    Optional<Role> findRoleByName(String name);





}

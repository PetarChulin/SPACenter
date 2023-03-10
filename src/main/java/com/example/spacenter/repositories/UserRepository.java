package com.example.spacenter.repositories;

import com.example.spacenter.model.entity.UserEntity;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    UserEntity getUsersById(Long id);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);


    Optional<UserEntity> findUserEntityByEmail(String email);
}

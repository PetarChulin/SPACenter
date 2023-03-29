package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private UserRepository userRepository;

    private final UserDetailsService detailsService;


    @Autowired
    public UserProfileService(UserRepository userRepository, UserDetailsService detailsService) {
        this.userRepository = userRepository;
        this.detailsService = detailsService;
    }

    public void updateUsername(Long id, String username) {

        this.userRepository.editUsername(id, username);
    }

    public void updatePassword(Long id, String password) {

        this.userRepository.editUserPassword(id, password);
    }

}

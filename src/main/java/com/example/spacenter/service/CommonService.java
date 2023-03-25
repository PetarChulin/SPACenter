package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CommonService {

    private static UserRepository userRepository;

    public CommonService(UserRepository userRepository) {
        CommonService.userRepository = userRepository;
    }

    static UserEntity getUserEntity() {
        Long userId = getUserId();

        return userRepository.getUsersById(userId);
    }

    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        return userDetails.getId();
    }
}

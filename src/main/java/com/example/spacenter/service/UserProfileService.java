package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserRepository userRepository;

    private final UserDetailsService detailsService;



    @Autowired
    public UserProfileService(UserRepository userRepository, UserDetailsService detailsService) {
        this.userRepository = userRepository;
        this.detailsService = detailsService;
    }

    public void updateUserDetails(Long id, String username, String password) {

        this.userRepository.editUsername(id, username);
        this.userRepository.editUserPassword(id, password);

        UserEntity currentUser = this.userRepository.findById(id).get();
        SecurityContextHolder.getContext()
                .setAuthentication(this.getAuthenticationToken(currentUser.getEmail()));
    }

    private Authentication getAuthenticationToken(String email) {
        UserDetails userDetails = detailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }
}

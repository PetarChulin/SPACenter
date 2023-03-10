package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.
                findUserEntityByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new AppUserDetails(
                        userEntity.getUsername(),
                        userEntity.getPassword(),
                extractAuthorities(userEntity),
                userEntity.getId()
                );
    }

    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
        return  userEntity.
                getRoles().
                stream().
                map(this::mapRole).
                toList();
    }

    private GrantedAuthority mapRole(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getName());
    }
}

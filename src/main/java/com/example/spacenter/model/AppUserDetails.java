package com.example.spacenter.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppUserDetails extends User {

    Long id;



    public AppUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities , Long id) {
        super(username, password, authorities);
        this.id = id;
    }

    public Long getId() {
        return id;
    }




}

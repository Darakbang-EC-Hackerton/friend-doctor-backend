package com.youngandhun.moduleapi.auth.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String email;
    private final String password;
    private final String authority;

    public CustomUserDetails(Long id, String email, String authority) {
        this.id = id;
        this.email = email;
        this.password = null;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> authority);

        return collection;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}

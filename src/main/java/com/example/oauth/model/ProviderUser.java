package com.example.oauth.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ProviderUser {
    String getId();
    String getUsername();
    String getPassword();
    String getEmail();
    String getProvider();
    String getBirthYear();
    List<? extends GrantedAuthority> getAuthorities();
    Map<String , Object> getAttributes();
}

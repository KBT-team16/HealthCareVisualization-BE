package com.example.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Getter
public class Authority implements GrantedAuthority {

    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}

package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String oauthId;
    private String registrationId;
    private String username;
    private String password;
    private String provider;
    private String email;
}

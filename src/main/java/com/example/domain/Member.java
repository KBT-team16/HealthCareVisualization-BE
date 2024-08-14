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
    private String username;
    private String email;
    private String role;
    private String socialPlatform;

    public void updateEmailForKakao(String uuid) {
        this.email = uuid;
    }
}

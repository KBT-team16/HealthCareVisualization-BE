package com.example.token;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenMapping {
    private final String accessToken;
    private String refreshToken;

    @Builder
    public TokenMapping(String accessToken , String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

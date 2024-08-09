package com.example.oauth.converters;

import com.example.domain.Member;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public record ProviderUserRequest(ClientRegistration clientRegistration , OAuth2User oAuth2User , Member member) {


    // Social 인증 방식 생성자
    public ProviderUserRequest(ClientRegistration clientRegistration , OAuth2User oAuth2User) {
        this(clientRegistration,oAuth2User,null);
    }
}

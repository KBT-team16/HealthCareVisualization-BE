package com.example.oauth.model.social;

import com.example.oauth.model.Attributes;
import com.example.oauth.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class NaverUser extends OAuth2ProviderUser {
    public NaverUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getSubAttributes() ,oAuth2User,clientRegistration);
    }

    @Override
    public String getId() {
        return (String) getAttributes().get("id");
    }
    @Override
    public String getUsername() {
        return (String) getAttributes().get("nickname");
    }

}

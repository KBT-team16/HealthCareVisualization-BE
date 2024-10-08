package com.example.oauth.service;

import com.example.oauth.converters.ProviderUserRequest;
import com.example.oauth.model.PrincipalUser;
import com.example.oauth.model.ProviderUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService
                                   implements OAuth2UserService<OidcUserRequest , OidcUser> {
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OidcUserRequest , OidcUser> oidcUserService = new OidcUserService();

        OidcUser oidcUser = oidcUserService.loadUser(userRequest);
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oidcUser);
        ProviderUser providerUser = providerUser(providerUserRequest);
        super.register(providerUser,userRequest);
        return new PrincipalUser(providerUser);
    }
}

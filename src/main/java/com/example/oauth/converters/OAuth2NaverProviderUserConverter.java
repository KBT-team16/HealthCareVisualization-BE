package com.example.oauth.converters;

import com.example.oauth.config.OAuth2Config;
import com.example.oauth.model.ProviderUser;
import com.example.oauth.model.social.NaverUser;
import com.example.oauth.util.OAuth2Utils;

public class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId()
                .equals(OAuth2Config.SocialType.NAVER.getSocialName())){
            return null;
        }

        return new NaverUser(OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(),"response"),
                providerUserRequest.oAuth2User(), providerUserRequest.clientRegistration());
    }
}

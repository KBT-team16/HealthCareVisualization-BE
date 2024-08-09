package com.example.oauth.service;

import com.example.domain.Member;
import com.example.oauth.model.OAuth2ProviderUser;
import com.example.oauth.model.ProviderUser;
import com.example.oauth.model.social.NaverUser;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    protected void register(ProviderUser providerUser , OAuth2UserRequest userRequest) {
        Member member = memberRepository.findByUsername(providerUser.getUsername());
        if (member == null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            memberService.register(registrationId,providerUser);
        }
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration , OAuth2User oAuth2User) {
        String registrationId = clientRegistration.getRegistrationId();

        if (registrationId.equals("naver")) {
            return new NaverUser(oAuth2User,clientRegistration);
        }
        return null;
    }



}

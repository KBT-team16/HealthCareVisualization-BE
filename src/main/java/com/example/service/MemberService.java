package com.example.service;

import com.example.domain.Member;
import com.example.oauth.model.ProviderUser;
import com.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void register(String registrationId , ProviderUser providerUser) {
        Member member = Member.builder()
                .registrationId(registrationId)
                .oauthId(providerUser.getId())
                .username(providerUser.getUsername())
                .email(providerUser.getEmail())
                .authorities(providerUser.getAuthorities())
                .build();
        memberRepository.save(member);
    }
}

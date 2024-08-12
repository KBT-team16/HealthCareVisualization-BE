package com.example.service;

import com.example.domain.Member;
import com.example.oauth.model.ProviderUser;
import com.example.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
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
                //.authorities(providerUser.getAuthorities())
                .build();
        memberRepository.save(member);
    }
}

package com.example.service;

import com.example.controller.request.InbodyDataDto;
import com.example.controller.response.MemberResponseDto;
import com.example.domain.InbodyData;
import com.example.domain.Member;
import com.example.oauth.model.ProviderUser;
import com.example.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.controller.response.MemberResponseDto.*;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private InbodyService inbodyService;

    @Transactional
    public void register(String registrationId , ProviderUser providerUser) {

        Member member = Member.builder()
                .socialPlatform(registrationId)
                .username(providerUser.getUsername())
                .email(providerUser.getEmail())
                .role("ROLE_USER")
                .build();
        // Kakao 에서 Email 정보를 알기 위해서는 심사를 통과해야 하기 때문에 Kakao 인 경우는 UUID 로 대체
        if (registrationId.equals("kakao")) {
            member.updateEmailForKakao(UUID.randomUUID().toString());
        }
        memberRepository.save(member);
    }

    public List<SingleMember> getMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(SingleMember::of).collect(Collectors.toList());
    }

    @Transactional
    public String saveInbodyData(InbodyDataDto inbodyDataDto) {
        Member member = memberRepository.findById(inbodyDataDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저 ID"));

        InbodyData inbodyData = inbodyService.saveInbodyData(inbodyDataDto);
        member.updateInbodyData(inbodyData);
        return "save success";
    }
}

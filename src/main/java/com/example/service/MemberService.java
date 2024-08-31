package com.example.service;

import com.example.controller.request.InbodyDataDto;
import com.example.controller.request.InbodyDto;
import com.example.controller.request.MemberRequestDto;
import com.example.controller.response.MemberResponseDto;
import com.example.domain.InbodyData;
import com.example.domain.Member;
import com.example.oauth.model.ProviderUser;
import com.example.repository.MemberRepository;
import com.example.token.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private InbodyClientService inbodyClientService;
    @Autowired
    private JwtService jwtService;

    @Transactional
    public void register(String registrationId , ProviderUser providerUser) {
        Member member = Member.builder()
                .socialPlatform(registrationId)
                .username(providerUser.getUsername())
                .email(providerUser.getEmail())
                .yearOfBirth(Integer.parseInt(providerUser.getBirthYear()))
                .role("ROLE_USER")
                .build();
        // Kakao 에서 Email 정보를 알기 위해서는 심사를 통과해야 하기 때문에 Kakao 인 경우는 UUID 로 대체
        if (registrationId.equals("kakao")) {
            member.updateEmailForKakao(UUID.randomUUID().toString());
        }
        memberRepository.save(member);

        memberRepository.flush();

        // 회원가입이 완료됐을 때 Inbody Server 로 데이터 전송
        inbodyClientService.transmitMemberData(InbodyDto.of(member.getId(),member.getYearOfBirth()));
        log.info("inbodyClientService 로 데이터 전송 성공");
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

    public EditInfo getMemberInfo(HttpServletRequest request) {
        Member member = findMemberFromAccessToken(request);
        return EditInfo.from(member.getHeight(),member.getWeight());
    }

    @Transactional
    public String editMemberInfo(MemberRequestDto.EditInfo editInfo , HttpServletRequest request) {
        Member member = findMemberFromAccessToken(request);
        member.updateInfo(editInfo);
        return "OK";
    }

    public InbodyHistories getInbodyHistories(HttpServletRequest request) {
        Member member = findMemberFromAccessToken(request);
        List<InbodyData> inbodyDatas = member.getInbodyDatas();
        return InbodyHistories.of(inbodyDatas.stream().map(InbodyHistory::from).collect(Collectors.toList()));
    }

    private Member findMemberFromAccessToken(HttpServletRequest request) {
        String accessToken = jwtService.extractAccessToken(request)
                .orElseThrow(() -> new RuntimeException("토큰이 유효하지 않습니다."));
        String email = jwtService.extractMemberEmail(accessToken);
        return memberRepository.findByEmail(email);
    }



}

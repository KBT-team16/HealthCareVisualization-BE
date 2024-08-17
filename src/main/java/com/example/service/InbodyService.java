package com.example.service;

import com.example.controller.response.InbodyResponseDto;
import com.example.domain.Gender;
import com.example.domain.InbodyData;
import com.example.domain.Member;
import com.example.oauth.model.PrincipalUser;
import com.example.repository.InbodyDataRepository;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.controller.response.InbodyResponseDto.*;

@Service
@RequiredArgsConstructor
public class InbodyService {

    private final MemberRepository memberRepository;
    private final InbodyDataRepository inbodyDataRepository;

    public SampleData getSampleData() {
        return SampleData.builder().dummy("dummy").build();
    }

    public Analyze analyze() {
        return Analyze
                .builder()
                .gender(Gender.MAN.name())
                .score(99)
                .fatDistribution("123")
                .recommedations("운동추천?")
                .build();
    }

    public List<SingleInbodyData> getHistories(PrincipalUser principalUser, Pageable pageable) {
        Member member = memberRepository.findByEmail(principalUser.providerUser().getEmail());
        Page<InbodyData> inbodyDatas = inbodyDataRepository.findAll(pageable, member.getId());
        return inbodyDatas.stream().map(SingleInbodyData::toDto).collect(Collectors.toList());
    }

    public SingleInbodyData getHistory(Long inbodyDataId) {
        InbodyData inbodydata = inbodyDataRepository.findById(inbodyDataId)
                .orElseThrow(() -> new RuntimeException("존재하지 안히는 인바디 데이터 ID"));
        return SingleInbodyData.toDto(inbodydata);
    }
}

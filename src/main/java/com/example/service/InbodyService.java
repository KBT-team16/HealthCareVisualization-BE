package com.example.service;

import com.example.controller.response.InbodyResponseDto;
import com.example.domain.Gender;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.controller.response.InbodyResponseDto.*;

@Service
@RequiredArgsConstructor
public class InbodyService {

    private MemberRepository memberRepository;

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


}

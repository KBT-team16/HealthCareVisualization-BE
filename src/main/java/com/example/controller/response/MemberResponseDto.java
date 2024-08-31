package com.example.controller.response;

import com.example.controller.request.InbodyDto;
import com.example.domain.InbodyData;
import com.example.domain.Member;
import com.example.token.TokenMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


public class MemberResponseDto {

    @Builder @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class SingleMember {
        private Long memberId;
        private int yearOfBirth;


        public static SingleMember of(Member member) {
            return SingleMember.builder()
                    .memberId(member.getId())
                    .yearOfBirth(member.getYearOfBirth())
                    .build();
        }
    }

    @Builder @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class LoginDto {
        private String name;
        private TokenMapping tokenMapping;

        public static LoginDto from(String name , TokenMapping token) {
            return LoginDto.builder()
                    .name(name)
                    .tokenMapping(token)
                    .build();
        }
    }

    @Builder @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class EditInfo {
        private float height;
        private float weight;


        public static EditInfo from(float height , float weight) {
            return EditInfo.builder()
                    .height(height)
                    .weight(weight)
                    .build();
        }
    }


    @Builder @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class InbodyHistories {
        private List<InbodyHistory> inbodyHistories;
        public static InbodyHistories of (List<InbodyHistory> inbodyHistories) {
            return InbodyHistories.builder()
                    .inbodyHistories(inbodyHistories)
                    .build();
        }
    }

    @Builder @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class InbodyHistory {
        private float bodyFatPercentage;
        // 근육량
        private float muscleMass;
        private float bmi;
        private float score;
        private LocalDateTime evaluationDate;

        public static InbodyHistory from(InbodyData inbodyData) {
            return InbodyHistory.builder()
                    .bmi(inbodyData.getBmi())
                    .bodyFatPercentage(inbodyData.getBodyFatPercentage())
                    .muscleMass(inbodyData.getMuscleMass())
                    .score(inbodyData.getScore())
                    .evaluationDate(inbodyData.getEvaluationDate())
                    .build();
        }

    }
}

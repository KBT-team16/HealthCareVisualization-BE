package com.example.controller.response;

import com.example.domain.InbodyData;
import lombok.*;

import java.time.LocalDateTime;

public class InbodyResponseDto {

    @Builder
    @Getter @NoArgsConstructor @AllArgsConstructor
    public static class SingleInbodyData {
        private Long id;
        private float muscleMass;
        private float score;
        private float bmi;
        private float bodyFatPercentage;
        private LocalDateTime date;

        public static SingleInbodyData toDto(InbodyData inbodyData) {
            return SingleInbodyData.builder()
                    .id(inbodyData.getId())
                    .bmi(inbodyData.getBmi())
                    .bodyFatPercentage(inbodyData.getBodyFatPercentage())
                    .muscleMass(inbodyData.getMuscleMass())
                    .score(inbodyData.getScore())
                    .date(inbodyData.getEvaluationDate())
                    .build();
        }
    }

    @Builder
    @Getter @NoArgsConstructor @AllArgsConstructor
    public static class SampleData {
        // 어떠한 값이 들어가야하는지 몰라서 우선 dummy 로 대체
        private String dummy;
    }

    @Builder
    @Getter @NoArgsConstructor @AllArgsConstructor
    public static class Analyze {
        // 어떠한 값이 들어가야하는지 몰라서 우선 dummy 로 대체
        private int yearOfBirth;
        private String gender;
        // 분석 점수
        private int score;
        // 운동 추천 (???)
        private String recommedations;
        // 체지방 분포 대시보드 데이터
        private String fatDistribution;

    }


}

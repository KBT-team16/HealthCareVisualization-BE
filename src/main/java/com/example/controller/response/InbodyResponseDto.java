package com.example.controller.response;

import lombok.*;

public class InbodyResponseDto {

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

package com.example.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InbodyDataDto {

    private IdInfo _id;
    private String userNumber;
    private String date;
    private String time;
    private String sex;
    private String birthYear;
    private String weight;
    private String bodyFat;
    private String skeletalMuscleMass;
    private String bodyFatPercentage;
    private String inbodyScore;
    private String weightControl;
    private String fatControl;
    private String muscleControl;
    private String basalMetabolicRate;
    private String visceralFatLevel;
    private String obesity;

    // 추가
    private Long memberId;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdInfo {
        private long timestamp;
        private String date;
    }
}

package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 인바디 기록
@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
public class InbodyData {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inbody_id")
    private Long id;
    // 체지방률
    private float bodyFatPercentage;
    // 근육량
    private float muscleMass;
    private float bmi;
    private float basalMetabolicRate;
    private float score;
    private LocalDateTime evaluationDate;
}

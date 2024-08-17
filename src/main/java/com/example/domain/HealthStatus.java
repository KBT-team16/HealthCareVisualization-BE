package com.example.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class HealthStatus {

    // 체지방률
    private float bodyFatPercentage;
    // 근육량
    private float muscleMass;
    private float bmi;
    private float basalMetabolicRate;
    private LocalDateTime evaluationDate;
}

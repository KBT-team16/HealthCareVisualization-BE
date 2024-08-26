package com.example.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Embeddable
@Getter
public class NutritionIntake {
    private int calories;
    private int protein;
    private int carbs;
    private int fats;
    private LocalDateTime recordDate;

}

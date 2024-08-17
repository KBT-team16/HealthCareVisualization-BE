package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String email;
    private String role;
    private String socialPlatform;

    // 신체 정보들
    private float height;
    private float weight;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Goal goal;
    @Embedded
    private HealthStatus healthStatus;
    @Embedded
    private NutritionIntake nutritionIntake;

    /**
     * OneToOne?
     * OneToMany?
     */
    @OneToOne
    @JoinColumn(name = "member_id")
    private ExerciseProgram exerciseProgram;

    public void updateEmailForKakao(String uuid) {
        this.email = uuid;
    }
}

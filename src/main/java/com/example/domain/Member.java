package com.example.domain;

import com.example.controller.request.InbodyDataDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private NutritionIntake nutritionIntake;

    // 출생년도
    private int yearOfBirth;

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<InbodyData> inbodyDatas = new ArrayList<>();

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

    public void updateInbodyData(InbodyData inbodyData) {
        this.inbodyDatas.add(inbodyData);
    }
}

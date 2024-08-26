package com.example.service;

import com.example.domain.Category;
import com.example.domain.ExerciseProgram;
import com.example.domain.Gender;
import com.example.domain.Member;
import com.example.repository.ExerciseProgramRepository;
import com.example.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class InitService {
    private final DataInitService dataInitService;


    @PostConstruct
    public void init() {
        // dataInitService.HighInit();
        // dataInitService.MiddleInit();
        // dataInitService.LowInit();
        dataInitService.memberInit();
    }


    @Service
    @RequiredArgsConstructor
    static class DataInitService {
        private final ExerciseProgramRepository exerciseProgramRepository;
        private final MemberRepository memberRepository;

        // 고강도
        public void HighInit() {
            // 유산소
            ExerciseProgram ex1 = ExerciseProgram.builder()
                    .category(Category.HIGH_AEROBIC)
                    .name("달리기")
                    .description("달리기")
                    .build();
            exerciseProgramRepository.save(ex1);

            ExerciseProgram ex2 = ExerciseProgram.builder()
                    .category(Category.HIGH_AEROBIC)
                    .name("자전거")
                    .description("자전거")
                    .build();
            exerciseProgramRepository.save(ex2);

            // 무산소
            ExerciseProgram ex3 = ExerciseProgram.builder()
                    .category(Category.HIGH_ANAEROBIC)
                    .name("웨이트 트레이닝")
                    .description("웨이트 트레이닝")
                    .build();
            exerciseProgramRepository.save(ex3);

            ExerciseProgram ex4 = ExerciseProgram.builder()
                    .category(Category.HIGH_ANAEROBIC)
                    .name("씨름")
                    .description("씨름")
                    .build();
            exerciseProgramRepository.save(ex4);
        }

        public void MiddleInit() {
            // 유산소
            ExerciseProgram ex1 = ExerciseProgram.builder()
                    .category(Category.MIDDLE_AEROBIC)
                    .name("배드민턴")
                    .description("배드민턴")
                    .build();
            exerciseProgramRepository.save(ex1);

            ExerciseProgram ex2 = ExerciseProgram.builder()
                    .category(Category.MIDDLE_AEROBIC)
                    .name("계단 오르기")
                    .description("계단 오르기")
                    .build();
            exerciseProgramRepository.save(ex2);

            // 무산소 추가
        }

        public void LowInit() {
            // 유산소
            ExerciseProgram ex1 = ExerciseProgram.builder()
                    .category(Category.LOW_AEROBIC)
                    .name("느린 춤")
                    .description("느린 춤")
                    .build();
            exerciseProgramRepository.save(ex1);

            ExerciseProgram ex2 = ExerciseProgram.builder()
                    .category(Category.LOW_AEROBIC)
                    .name("걷기")
                    .description("걷기")
                    .build();
            exerciseProgramRepository.save(ex2);

            // 무산소 추가
        }

        public void memberInit() {
            Member member1 = Member.builder()
                    .email("init@")
                    .username("init")
                    .gender(Gender.MAN)
                    .yearOfBirth(2000)
                    .build();
            memberRepository.save(member1);

            Member member2 = Member.builder()
                    .email("init@")
                    .username("init")
                    .gender(Gender.MAN)
                    .yearOfBirth(1970)
                    .build();
            memberRepository.save(member2);
        }

    }
}

package com.example.controller.request;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;


@Builder @NoArgsConstructor
@AllArgsConstructor
@Getter
public class InbodyDto {
    private Long memberId;
    private int yearOfBirth;
    public static InbodyDto of(Long memberId , int yearOfBirth) {
        return InbodyDto.builder()
                .memberId(memberId)
                .yearOfBirth(yearOfBirth)
                .build();
    }
}

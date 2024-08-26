package com.example.controller.response;

import com.example.domain.InbodyData;
import com.example.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class MemberResponseDto {

    @Builder @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class SingleMember {
        private Long memberId;
        private int yearOfBirth;


        public static SingleMember of(Member member) {
            return SingleMember.builder()
                    .memberId(member.getId())
                    .yearOfBirth(member.getYearOfBirth())
                    .build();
        }
    }
}

package com.example.controller.request;

import com.example.controller.response.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EditInfo {
        private float height;
        private float weight;
        public static MemberResponseDto.EditInfo from(float height , float weight) {
            return MemberResponseDto.EditInfo.builder()
                    .height(height)
                    .weight(weight)
                    .build();
        }
    }
}

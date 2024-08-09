package com.example.oauth.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Attributes {
    // 바로 가져올 수 있는 속성은 mainAttribute
    private Map<String, Object> mainAttributes;
    // 한 단계 더 들어가야하는 속성은 subAttributes
    private Map<String, Object> subAttributes;
    // 한 단계 그 이상 더 들어가야 하는 속성은 otherAttributes
    private Map<String, Object> otherAttributes;
}

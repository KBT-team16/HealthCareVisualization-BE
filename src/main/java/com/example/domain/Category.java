package com.example.domain;

public enum Category {

    HIGH_ANAEROBIC("고강도 무산소"),
    HIGH_AEROBIC("고강도 유산소"),
    MIDDLE_ANAEROBIC("중강도 무산소"),
    MIDDLE_AEROBIC("중강도 유산소"),
    LOW_ANAEROBIC("저강도 무산소"),
    LOW_AEROBIC("저강도 유산소"),
    ;

    private final String description;

    Category(String description) {
        this.description = description;
    }
}

package com.example.domain;

public enum Goal {

    DIET("다이터트") , BULK_UP("벌크업");

    private final String description;

    Goal(String description) {
        this.description = description;
    }
}

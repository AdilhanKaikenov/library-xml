package com.epam.adk.task3.library_xml.entity;

public enum Genre {

    FANTASY("Fantasy"),
    POETRY("Poetry"),
    DETECTIVE("Detective"),
    NOVEL("Novel"),
    COOKERY("Cookery"),
    PROGRAMMING("Programming");

    private String value;

    Genre(String value) {
        this.value = value;
    }
}

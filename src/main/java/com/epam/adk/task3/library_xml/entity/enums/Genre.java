package com.epam.adk.task3.library_xml.entity.enums;

/**
 * The enum class Genre. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
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
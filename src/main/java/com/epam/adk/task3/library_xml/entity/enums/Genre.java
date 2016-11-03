package com.epam.adk.task3.library_xml.entity.enums;

import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;

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

    public String getValue() {
        return value;
    }

    public static Genre from(String value){
        for (Genre genre : Genre.values()){
            if (value != null && genre.getValue().equals(value)){
                return genre;
            }
        }
        throw new IllegalArgumentException();
    }
}

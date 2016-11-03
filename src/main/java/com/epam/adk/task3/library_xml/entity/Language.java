package com.epam.adk.task3.library_xml.entity;

/**
 * The enum class Language. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public enum Language {

    RUSSIAN("Russian"),
    ENGLISH("English"),
    KAZAKH("Kazakh");

    private String value;

    Language(String value) {
        this.value = value;
    }
}

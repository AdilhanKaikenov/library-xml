package com.epam.adk.task3.library_xml.entity.enums;

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

    public String getValue() {
        return value;
    }

    public static Language from(String value){
        for (Language language : Language.values()){
            if (value != null && language.getValue().equals(value)){
                return language;
            }
        }
        throw new IllegalArgumentException();
    }
}

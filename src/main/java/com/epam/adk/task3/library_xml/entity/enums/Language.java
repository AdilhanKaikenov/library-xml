package com.epam.adk.task3.library_xml.entity.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The enum class Language. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Language", namespace = "http://epam-xml-library.com/library_xml")
@XmlEnum
public enum Language {

    @XmlEnumValue(value = "Russian")
    RUSSIAN("Russian"),
    @XmlEnumValue(value = "English")
    ENGLISH("English"),
    @XmlEnumValue(value = "Kazakh")
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

package com.epam.adk.task3.library_xml.entity.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The enum class Genre. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Genre", namespace = "http://epam-xml-library.com/library_xml")
@XmlEnum
public enum Genre {

    @XmlEnumValue(value = "Fantasy")
    FANTASY("Fantasy"),
    @XmlEnumValue(value = "Poetry")
    POETRY("Poetry"),
    @XmlEnumValue(value = "Detective")
    DETECTIVE("Detective"),
    @XmlEnumValue(value = "Novel")
    NOVEL("Novel"),
    @XmlEnumValue(value = "Cookery")
    COOKERY("Cookery"),
    @XmlEnumValue(value = "Programming")
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

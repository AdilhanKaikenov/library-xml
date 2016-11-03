package com.epam.adk.task3.library_xml.parser.enums;

/**
 * The enum class ElementEnum. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public enum ElementEnum {

    LIBRARY("library"),
    BOOKS("books"),
    BOOK("book"),
    ISBN("isbn"),
    TITLE("title"),
    GENRE("genre"),
    AUTHORS("authors"),
    AUTHOR("author"),
    NUMBER_OF_PUBLISHING("numberOfPages"),
    YEAR_OF_PUBLISHING("yearOfPublishing"),
    LANGUAGE("language");

    private String value;

    ElementEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ElementEnum from(String value){
        for (ElementEnum elementEnum : ElementEnum.values()){
            if (value != null && elementEnum.getValue().equals(value)){
                return elementEnum;
            }
        }
        throw new IllegalArgumentException();
    }
}

package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.time.Year;

/**
 * The class Book. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Book", propOrder = {"isbn", "title", "genre", "authors", "numberOfPages", "yearOfPublishing", "language"},
        namespace = "http://epam.com/library")
public class Book {

    private String isbn;
    private String title;
    private Genre genre;
    private Authors authors;
    private Integer numberOfPages;
    private Year yearOfPublishing;
    private Language language;

    public Book() {
    }

    public Book(String isbn, String title, Genre genre, Authors authors,
                Integer numberOfPages, Year year, Language language) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.yearOfPublishing = year;
        this.language = language;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public String getYearOfPublishing() {
        return yearOfPublishing.toString();
    }

    public void setYearOfPublishing(Year yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "\n * book: " +
                "\n\t -> isbn = " + isbn +
                "\n\t -> title = " + title +
                "\n\t -> genre = " + genre + authors +
                "\n\t -> numberOfPages = " + numberOfPages +
                "\n\t -> yearOfPublishing = " + yearOfPublishing +
                "\n\t -> language = " + language + ".";
    }

    /**
     * The enum inner class Genre. Created on 03.11.2016.
     *
     * @author Kaikenov Adilhan.
     */
    @XmlType(name = "Genre", namespace = "http://epam.com/library")
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

    /**
     * The enum inner class Language. Created on 03.11.2016.
     *
     * @author Kaikenov Adilhan.
     */
    @XmlType(name = "Language", namespace = "http://epam.com/library")
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
}

package com.epam.adk.task3.library_xml.entity;

import com.epam.adk.task3.library_xml.entity.enums.Genre;
import com.epam.adk.task3.library_xml.entity.enums.Language;

import java.time.Year;

/**
 * The class Book. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class Book implements BaseEntity {

    private String isbn;
    private String title;
    private Genre genre;
    private Authors authors;
    private Integer numberOfPages;
    private Year year;
    private Language language;

    public Book() {
    }

    public Book(String isbn, String title, Genre genre, Authors authors, Integer numberOfPages, Year year, Language language) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.year = year;
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public void setYear(int year) {
        this.year = Year.of(year);
    }

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
                "\n\t -> genre= " + genre + authors +
                "\n\t -> numberOfPages = " + numberOfPages +
                "\n\t -> year = " + year + ".";
    }
}

package com.epam.adk.task3.library_xml.entity;

import java.time.Year;

public class Book {

    private String isbn;
    private String title;
    private Genre genre;
    private Authors authors;
    private Integer numberOfPages;
    private Year year;

    public Book() {
    }

    public Book(String isbn, String title, Genre genre, Authors authors, Integer numberOfPages, Year year) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.year = year;
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

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", authors=" + authors +
                ", numberOfPages=" + numberOfPages +
                ", year=" + year +
                '}';
    }
}

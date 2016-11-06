package com.epam.adk.task3.library_xml.entity;

import com.epam.adk.task3.library_xml.entity.enums.Genre;
import com.epam.adk.task3.library_xml.entity.enums.Language;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.Year;
import java.util.List;

/**
 * The class Book. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Book", propOrder = {"isbn", "title", "genre", "authors", "numberOfPages", "yearOfPublishing", "language"},
        namespace = "http://epam-xml-library.com/library_xml")
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

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors.setAuthors(authors);
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    public String getYearOfPublishing() {
        return yearOfPublishing.toString();
    }

    public void setYearOfPublishing(Year yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public void setYear(int year) {
        this.yearOfPublishing = Year.of(year);
    }

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
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
                "\n\t -> yearOfPublishing = " + yearOfPublishing + ".";
    }
}

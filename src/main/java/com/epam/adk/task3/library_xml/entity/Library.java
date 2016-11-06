package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.*;

/**
 * The class Library. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Library", propOrder = {"books"})
@XmlRootElement(name = "library", namespace = "http://epam-xml-library.com/library_xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    private Books books;

    public Library() {
        books = new Books();
    }

    public Library(Books books) {
        this.books = books;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                '}';
    }
}

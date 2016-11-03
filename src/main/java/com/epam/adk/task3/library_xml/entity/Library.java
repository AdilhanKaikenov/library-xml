package com.epam.adk.task3.library_xml.entity;

/**
 * The class Library. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class Library {

    private Books books;

    public Library() {
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

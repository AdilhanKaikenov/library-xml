package com.epam.adk.task3.library_xml.entity;

import java.util.List;

/**
 * The class Books. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class Books implements BaseEntity {

    private List<Book> books;

    public Books() {
    }

    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Books{" +
                "books=" + books +
                '}';
    }
}

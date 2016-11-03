package com.epam.adk.task3.library_xml.entity;

import java.util.List;

public class Books {

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

package com.epam.adk.task3.library_xml.entity;

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

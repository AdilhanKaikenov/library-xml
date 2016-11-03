package com.epam.adk.task3.library_xml.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Books. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class Books implements BaseEntity {

    private List<Book> books;

    public Books() {
        books = new ArrayList<>();
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

    public boolean add(Book book){
        return books.add(book);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append("books: ").append(book);
        }
        return  sb.toString();
    }
}

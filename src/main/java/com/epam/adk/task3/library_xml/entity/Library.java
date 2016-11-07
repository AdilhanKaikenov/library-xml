package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Book. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Library", propOrder = {"books"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {

    @XmlElement(namespace = "http://epam.com/library")
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> book) {
        this.books = book;
    }

    public boolean add(Book book){
        return this.books.add(book);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("books: ");
        for (Book book : this.books) {
            sb.append(book);
        }
        return  sb.toString();
    }
}

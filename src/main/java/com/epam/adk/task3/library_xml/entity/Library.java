package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Book. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Library", propOrder = {"book"})
@XmlRootElement(name = "library", namespace = "http://epam.com/library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {

    @XmlElement(namespace = "http://epam.com/library")
    private List<Book> book;

    public Library() {
        book = new ArrayList<>();
    }

    public Library(List<Book> books) {
        this.book = books;
    }

    public List<Book> getBooks() {
        return book;
    }

    public void setBooks(List<Book> book) {
        this.book = book;
    }

    public boolean add(Book book){
        return this.book.add(book);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("books: ");
        for (Book book : this.book) {
            sb.append(book);
        }
        return  sb.toString();
    }
}

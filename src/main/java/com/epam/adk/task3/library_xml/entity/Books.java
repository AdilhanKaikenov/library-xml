package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Book. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Books", propOrder = {"book"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Books {

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
    private List<Book> book;

    public Books() {
        book = new ArrayList<>();
    }

    public Books(List<Book> books) {
        this.book = books;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public boolean add(Book book){
        return this.book.add(book);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : this.book) {
            sb.append("book: ").append(book);
        }
        return  sb.toString();
    }
}

package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Book. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Library", propOrder = {"id", "name", "address", "books"}, namespace = "http://epam.com/library")
@XmlRootElement(name = "library", namespace = "http://epam.com/library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {

    @XmlElement(name = "id", namespace = "http://epam.com/library")
    private String id;

    @XmlElement(name = "name", namespace = "http://epam.com/library")
    private String name;

    @XmlElement(name = "address", namespace = "http://epam.com/library")
    private String address;

    @XmlElement(name = "book", namespace = "http://epam.com/library")
    @XmlElementWrapper(namespace = "http://epam.com/library")
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public Library(String id, String name, String address, List<Book> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        sb.append("\nID: ").append(id).append("\n");
        sb.append("name: ").append(name).append("\n");
        sb.append("address: ").append(address).append("\n");
        sb.append("books: ");
        for (Book book : this.books) {
            sb.append(book);
        }
        return  sb.toString();
    }
}

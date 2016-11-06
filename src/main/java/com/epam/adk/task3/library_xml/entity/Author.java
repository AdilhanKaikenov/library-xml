package com.epam.adk.task3.library_xml.entity;

/**
 * The class Author. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class Author {

    private String author;

    public Author() {
    }

    public Author(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "\n\t\t - '" + author + '\'';
    }
}

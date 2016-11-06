package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The class Author. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Author", propOrder = { "author" })
public class Author {

    private String author;

    public Author() {
    }

    public Author(String author) {
        this.author = author;
    }

    @XmlElement(namespace = "http://epam-xml-library.com/library_xml")
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

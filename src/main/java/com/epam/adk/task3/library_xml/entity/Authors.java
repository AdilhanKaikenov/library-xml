package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Authors. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Authors", propOrder = { "author" }, namespace = "http://epam.com/library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Authors {

    @XmlElement(namespace = "http://epam.com/library")
    private List<String> author;

    public Authors() {
        author = new ArrayList<>();
    }

    public Authors(List<String> authors) {
        this.author = authors;
    }

    public List<String> getAuthors() {
        return author;
    }

    public void setAuthors(List<String> authors) {
        this.author = authors;
    }

    public boolean add(String author){
        return this.author.add(author);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t -> authors: ");
        for (String author : this.author){
            sb.append("\n\t\t\t").append(author);
        }
        return sb.toString();
    }
}

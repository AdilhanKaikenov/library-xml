package com.epam.adk.task3.library_xml.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Authors. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
@XmlType(name = "Authors", propOrder = { "authors" })
public class Authors {

    private List<String> authors;

    public Authors() {
        authors = new ArrayList<>();
    }

    public Authors(List<String> authors) {
        this.authors = authors;
    }

    @XmlElement(namespace = "http://epam.com/library")
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public boolean add(String author){
        return authors.add(author);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t -> authors: ");
        for (String author : authors){
            sb.append("\n\t\t\t").append(author);
        }
        return sb.toString();
    }
}

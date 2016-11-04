package com.epam.adk.task3.library_xml.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Authors. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class Authors implements BaseEntity {

    private List<Author> authors;

    public Authors() {
        authors = new ArrayList<>();
    }

    public Authors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public boolean add(Author author){
        return authors.add(author);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\t -> authors: ");
        for (Author author : authors){
            sb.append(author);
        }
        return sb.toString();
    }
}

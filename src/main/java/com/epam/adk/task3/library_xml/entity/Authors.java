package com.epam.adk.task3.library_xml.entity;

import java.util.List;

/**
 * The class Authors. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class Authors {

    private List<Author> authors;

    public Authors() {
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

    @Override
    public String toString() {
        return "Authors{" +
                "authors=" + authors +
                '}';
    }

    /**
     * Inner class Author.
     */
    private class Author {

        private String name;

        public Author() {
        }

        public Author(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}

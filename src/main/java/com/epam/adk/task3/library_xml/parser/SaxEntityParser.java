package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.*;
import com.epam.adk.task3.library_xml.entity.enums.Genre;
import com.epam.adk.task3.library_xml.entity.enums.Language;
import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;

/**
 * The SaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class SaxEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(SaxEntityParser.class);

    private SaxParserHandler handler = new SaxParserHandler();

    @Override
    public <C extends BaseEntity> C parse(InputStream inputStream, Class<C> clazz) {

        BaseEntity baseEntity = null;

        SAXParserFactory saxParserF = SAXParserFactory.newInstance();
        SAXParser parser;
        try (InputStream is = inputStream) {
            parser = saxParserF.newSAXParser();
            parser.parse(is, handler);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            log.error("The error in the method 'parse()' of class SAX: " + e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            baseEntity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Error in parse() method. baseEntity = {}", baseEntity);
        }

        if (clazz.equals(Library.class)){
            baseEntity = handler.getLibrary();
        } else if (clazz.equals(Books.class)){
            baseEntity = handler.getBooks();
        }

        if (baseEntity == null){
            throw new NullPointerException("Error in parse() method. baseEntity = null");
        }

        return (C) baseEntity;
    }


    /**
     * Inner class SaxParserHandler.
     */
    private class SaxParserHandler extends DefaultHandler {

        private String currentElement;
        private Library library;
        private Books books;
        private Book book;
        private Authors authors;
        private Authors.Author author;
        private ElementEnum elementEnum;

        public SaxParserHandler() {
        }

        public Library getLibrary() {
            return library;
        }

        public Books getBooks() {
            return books;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElement = qName;
            log.trace("startElement() method element = {}", qName);
            if (currentElement.equals(ElementEnum.LIBRARY.getValue())) {
                library = new Library();
            } else if (currentElement.equals(ElementEnum.BOOKS.getValue())) {
                books = new Books();
            } else if (currentElement.equals(ElementEnum.BOOK.getValue())) {
                book = new Book();
            } else if (currentElement.equals(ElementEnum.AUTHORS.getValue())) {
                authors = new Authors();
            } else {
                ElementEnum element = ElementEnum.from(currentElement);
                if (element != null) {
                    elementEnum = element;
                    log.trace("startElement() method elementEnum = {}", elementEnum);
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            currentElement = qName;
            log.trace("endElement() method element = {}", currentElement);
            if (currentElement.equals(ElementEnum.AUTHORS.getValue())) {
                book.setAuthors(authors);
            } else if (currentElement.equals(ElementEnum.BOOK.getValue())) {
                books.add(book);
            } else if (currentElement.equals(ElementEnum.LIBRARY.getValue())) {
                library.setBooks(books);
            }
            elementEnum = null;
            currentElement = "";
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = new String(ch, start, length);
            if (elementEnum != null) {
                switch (elementEnum) {
                    case ISBN:
                        book.setIsbn(content);
                        break;
                    case TITLE:
                        book.setTitle(content);
                        break;
                    case GENRE:
                        book.setGenre(Genre.from(content));
                        break;
                    case AUTHOR:
                        author = new Authors.Author();
                        author.setName(content);
                        authors.add(author);
                        break;
                    case NUMBER_OF_PUBLISHING:
                        book.setNumberOfPages(Integer.parseInt(content));
                        break;
                    case YEAR_OF_PUBLISHING:
                        book.setYear(Year.parse(content));
                        break;
                    case LANGUAGE:
                        book.setLanguage(Language.from(content));
                        break;
                }
            }
        }
    }
}
package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.Author;
import com.epam.adk.task3.library_xml.entity.Authors;
import com.epam.adk.task3.library_xml.entity.Book;
import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.entity.enums.Genre;
import com.epam.adk.task3.library_xml.entity.enums.Language;
import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.text.MessageFormat;
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
    public Library parse(String xmlFilePath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try (InputStream is = SaxParserHandler.class.getClassLoader().getResourceAsStream(xmlFilePath)) {
            parser = factory.newSAXParser();
            parser.parse(is, handler);
            log.debug("SaxEntityParser the parse method executed!");
        } catch (Exception e) {
            log.error("The error in the method 'parse()' of class SaxEntityParser: {}", e);
            throw new RuntimeException(MessageFormat.format("" +
                    "The error in the method 'parse()' of class SaxEntityParser: {0}", e));
        }
        return handler.getLibrary();
    }

    /**
     * Inner class SaxParserHandler.
     */
    private class SaxParserHandler extends DefaultHandler {

        private Library library;
        private Book book;
        private Authors authors;
        private Author author;
        private ElementEnum elementEnum;

        public SaxParserHandler() {
        }

        public Library getLibrary() {
            return library;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            ElementEnum startElement = ElementEnum.from(qName);
            log.trace("startElement() method element = {}", qName);

            if (startElement != null) {
                switch (startElement) {
                    case LIBRARY:
                        library = new Library();
                        break;
                    case BOOK:
                        book = new Book();
                        break;
                    case AUTHORS:
                        authors = new Authors();
                        break;
                    default:
                        elementEnum = startElement;
                }
            }
            log.trace("startElement() method elementEnum = {}", elementEnum);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            ElementEnum endElement = ElementEnum.from(qName);
            log.trace("endElement() method element = {}", qName);
            if (endElement != null) {
                switch (endElement) {
                    case AUTHORS:
                        book.setAuthors(authors);
                        break;
                    case BOOK:
                        library.add(book);
                        break;
                    default:
                        elementEnum = null;
                }
            }
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
                        author = new Author();
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
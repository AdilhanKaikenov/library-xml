package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.Authors;
import com.epam.adk.task3.library_xml.entity.Book;
import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;
import com.epam.adk.task3.library_xml.util.ElementsContentInitialiser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The SaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class SaxEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(SaxEntityParser.class);

    private SaxParserHandler handler = new SaxParserHandler();

    @Override
    public Library parse(InputStream inputStream) {
        log.debug("Entering SaxEntityParser class, parse() ");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;

        try (InputStream is = inputStream) {
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

        private StringBuilder content = new StringBuilder();
        private Library library;
        private Book book;
        private List<Book> bookList;
        private Authors authors;
        private ElementEnum elementEnum;

        public Library getLibrary() {
            return library;
        }

        public SaxParserHandler() {
            bookList = new ArrayList<>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            ElementEnum startElement = ElementEnum.from(qName);
            log.trace("startElement() method current element = {}, startElement enumeration = {}", qName, startElement);

            content.setLength(0);

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
            log.trace("endElement() method current element = {}, endElement enumeration = {}", qName, endElement);

            if (endElement != null) {
                switch (endElement) {
                    case LIBRARY:
                        library.setBooks(bookList);
                        break;
                    case BOOK:
                        bookList.add(book);
                        break;
                    case AUTHORS:
                        book.setAuthors(authors);
                        break;
                    default:
                        elementEnum = null;
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content.append(ch, start, length);
            ElementsContentInitialiser.initialize(elementEnum, book, authors, content.toString());
        }
    }
}
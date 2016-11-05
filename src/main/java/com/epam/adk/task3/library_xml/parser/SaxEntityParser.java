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
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

/**
 * The SaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class SaxEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(SaxEntityParser.class);
    private static final String RESOURCES_LIBRARY_XSD_PATH = "src\\main\\resources\\library.xsd";

    private SaxParserHandler handler = new SaxParserHandler();

    @Override
    public Library parse(String resourcesXMLFilePath) {
        log.debug("Entering SaxEntityParser class, parse( Argument: resourcesXMLFilePath = {}) ", resourcesXMLFilePath);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;

        try (InputStream is = SaxParserHandler.class.getClassLoader().getResourceAsStream(resourcesXMLFilePath)) {
            parser = factory.newSAXParser();

            validate(resourcesXMLFilePath);

            parser.parse(is, handler);


            log.debug("SaxEntityParser the parse method executed!");
        } catch (Exception e) {
            log.error("The error in the method 'parse()' of class SaxEntityParser: {}", e);
            throw new RuntimeException(MessageFormat.format("" +
                    "The error in the method 'parse()' of class SaxEntityParser: {0}", e));
        }
        return handler.getLibrary();
    }

    private void validate(String resourcesXMLFilePath) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(("http://www.w3.org/2001/XMLSchema"));
        File schemaLocation = new File(RESOURCES_LIBRARY_XSD_PATH);
        Schema schema = schemaFactory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        try (InputStream is = SaxParserHandler.class.getClassLoader().getResourceAsStream(resourcesXMLFilePath)) {
            Source source = new StreamSource(is);
            try {
                validator.validate(source);
                log.debug("{} file is valid!", resourcesXMLFilePath);
            } catch (SAXException e) {
                log.error("{} file is not valid!", resourcesXMLFilePath);
                throw new RuntimeException(MessageFormat.format(
                        "{0} file is not valid!", e));
            }
        }
    }

    /**
     * Inner class SaxParserHandler.
     */
    private class SaxParserHandler extends DefaultHandler {

        private Library library;
        private Book book;
        private Authors authors;
        private ElementEnum elementEnum;

        public Library getLibrary() {
            return library;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            ElementEnum startElement = ElementEnum.from(qName);
            log.trace("startElement() method current element = {}, startElement enumeration = {}", qName, startElement);

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
            ElementsContentInitialiser.initialize(elementEnum, content, book, authors);
        }
    }
}
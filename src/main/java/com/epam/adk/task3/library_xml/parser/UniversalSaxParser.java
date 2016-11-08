package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.exception.ParsingException;
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
import java.text.MessageFormat;
import java.util.Deque;

/**
 * The UniversalSaxParser class. Created on 08.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class UniversalSaxParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(UniversalSaxParser.class);

    private UniversalSaxParser.UniversalSaxHandler handler = new UniversalSaxParser.UniversalSaxHandler();

    @Override
    public Library parse(InputStream inputStream) throws ParsingException {
        log.debug("Entering UniversalSaxParser class, parse() ");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;

        try (InputStream is = inputStream) {
            parser = factory.newSAXParser();

            parser.parse(is, handler);

            log.debug("UniversalSaxParser the parse method executed!");

        } catch (SAXException | ParserConfigurationException | IOException e) {
            log.error("The error in the method 'parse()' of class UniversalSaxParser: {}", e);
            throw new ParsingException(MessageFormat.format("" +
                    "The error in the method 'parse()' of class UniversalSaxParser: {0}", e));
        }
        return (Library) handler.currentObject;
    }

    private class UniversalSaxHandler extends DefaultHandler {

        StringBuilder content = new StringBuilder();
        Object currentObject;
        Deque<Object> stack;

        public UniversalSaxHandler() {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            content.setLength(0);

            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }
    }
}

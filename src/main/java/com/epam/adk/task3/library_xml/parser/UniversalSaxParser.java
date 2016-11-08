package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.exception.ParsingException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.Deque;

/**
 * The UniversalSaxParser class. Created on 08.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class UniversalSaxParser implements EntityParser {
    @Override
    public Library parse(InputStream inputStream) throws ParsingException {
        return null;
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

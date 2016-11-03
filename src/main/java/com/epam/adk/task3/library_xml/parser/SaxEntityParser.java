package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.BaseEntity;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.List;

/**
 * The SaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class SaxEntityParser implements EntityParser {

    @Override
    public <T extends BaseEntity> T parse(InputStream inputStream, Class<T> clazz) {
        return null;
    }

    @Override
    public <T extends BaseEntity> List<T> parse(InputStream inputStream, List list, Class<T> clazz) {
        return null;
    }

    /**
     * Inner class SaxParserHandler.
     */
    private class SaxParserHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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

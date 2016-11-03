package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.Collection;

/**
 * The SaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class SaxEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(SaxEntityParser.class);

    @Override
    public <C extends BaseEntity> C parse(InputStream inputStream, Class<C> clazz) {
        return null;
    }

    @Override
    public <T extends Collection<C>, C extends BaseEntity> T parse(InputStream inputStream, Class<T> collectionClass, Class<C> clazz) {
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

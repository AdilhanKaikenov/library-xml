package com.epam.adk.task3.library_xml.main;

import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.parser.SaxEntityParser;
import com.epam.adk.task3.library_xml.parser.StaxEntityParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class with main method. Created on 1.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class XMLApp {

    private static final Logger log = LoggerFactory.getLogger(XMLApp.class);
    private static final String XML_FILE_PATH = "library.xml";

    /**
     * Application starting point.
     *
     * @param args input arguments array.
     */
    public static void main(String[] args) {

        SaxEntityParser parserSAX = new SaxEntityParser();
        Library libraryOne = parserSAX.parse(XML_FILE_PATH);
        log.info("SaxEntityParser: Library = {}", libraryOne);

        StaxEntityParser parserStAX = new StaxEntityParser();
        Library libraryTwo = parserStAX.parse(XML_FILE_PATH);
        log.info("StaxEntityParser: Library = {}", libraryTwo);

    }
}

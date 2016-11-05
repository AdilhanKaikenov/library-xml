package com.epam.adk.task3.library_xml.main;

import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.parser.SaxEntityParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The class with main method. Created on 1.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class XMLApp {

    private static final Logger log = LoggerFactory.getLogger(XMLApp.class);

    /**
     * Application starting point.
     *
     * @param args input arguments array.
     */
    public static void main(String[] args) throws FileNotFoundException {

        InputStream inputStream = XMLApp.class.getClassLoader().getResourceAsStream("library.xml");

        SaxEntityParser parser = new SaxEntityParser();
        Library library = parser.parse(inputStream);

        log.info("SaxEntityParser: Library = {}", library);

    }
}

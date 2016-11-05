package com.epam.adk.task3.library_xml.main;

import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.parser.DomEntityParser;
import com.epam.adk.task3.library_xml.parser.SaxEntityParser;
import com.epam.adk.task3.library_xml.parser.StaxEntityParser;
import com.epam.adk.task3.library_xml.util.XmlParserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class with main method. Created on 1.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class XMLApp {

    private static final Logger log = LoggerFactory.getLogger(XMLApp.class);
    private static final String RESOURCES_LIBRARY_XML_PATH = "src\\main\\resources\\library.xml";
    private static final String RESOURCES_LIBRARY_XSD_PATH = "src\\main\\resources\\library.xsd";
    private static final String RESOURCES_XML_FILE_PATH = "library.xml";

    /**
     * Application starting point.
     *
     * @param args input arguments array.
     */
    public static void main(String[] args) {

        XmlParserValidator validator = new XmlParserValidator();
        validator.validateXMLByXSD(RESOURCES_LIBRARY_XML_PATH, RESOURCES_LIBRARY_XSD_PATH);

        SaxEntityParser saxParser = new SaxEntityParser();
        Library library1 = saxParser.parse(RESOURCES_XML_FILE_PATH);
        log.info("SaxEntityParser: Library = {}", library1);

        StaxEntityParser staxParser = new StaxEntityParser();
        Library library2 = staxParser.parse(RESOURCES_XML_FILE_PATH);
        log.info("StaxEntityParser: Library = {}", library2);

        DomEntityParser domParser = new DomEntityParser();
        Library library3 = domParser.parse(RESOURCES_XML_FILE_PATH);
        log.info("DomEntityParser: Library = {}", library3);

    }
}

package com.epam.adk.task3.library_xml.main;

import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.parser.DomEntityParser;
import com.epam.adk.task3.library_xml.parser.SaxEntityParser;
import com.epam.adk.task3.library_xml.parser.StaxEntityParser;
import com.epam.adk.task3.library_xml.util.XmlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * The class with main method. Created on 1.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class XMLApp {

    private static final Logger log = LoggerFactory.getLogger(XMLApp.class);
    private static final String RESOURCES_XSD_FILE_PATH = "library.xsd";
    private static final String RESOURCES_XML_FILE_PATH = "library.xml";

    /**
     * Application starting point.
     *
     * @param args input arguments array.
     */
    public static void main(String[] args) {

        InputStream xmlInputStream = XMLApp.class.getClassLoader().getResourceAsStream(RESOURCES_XML_FILE_PATH);

        String xmlFile = XMLApp.class.getClassLoader().getResource(RESOURCES_XML_FILE_PATH).getFile();
        String xsdFile = XMLApp.class.getClassLoader().getResource(RESOURCES_XSD_FILE_PATH).getFile();

        XmlValidator validator = new XmlValidator();
        validator.validateXMLByXSD(new File(xmlFile), new File(xsdFile));

        SaxEntityParser saxParser = new SaxEntityParser();
        Library library1 = saxParser.parse(xmlInputStream);
        log.info("SaxEntityParser: Library = {}", library1);

        StaxEntityParser staxParser = new StaxEntityParser();
        Library library2 = staxParser.parse(xmlInputStream);
        log.info("StaxEntityParser: Library = {}", library2);

        DomEntityParser domParser = new DomEntityParser();
        Library library3 = domParser.parse(xmlInputStream);
        log.info("DomEntityParser: Library = {}", library3);

//        XmlWriter writer = new JAXBWriter();
//        writer.writeTo("marshal-library.xml", library1);
    }
}

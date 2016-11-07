package com.epam.adk.task3.library_xml.main;

import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.exception.ParsingException;
import com.epam.adk.task3.library_xml.parser.DomEntityParser;
import com.epam.adk.task3.library_xml.parser.SaxEntityParser;
import com.epam.adk.task3.library_xml.parser.StaxEntityParser;
import com.epam.adk.task3.library_xml.util.XmlValidator;
import com.epam.adk.task3.library_xml.writer.JAXBWriter;
import com.epam.adk.task3.library_xml.writer.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.InputStream;
import java.text.MessageFormat;

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
    public static void main(String[] args) throws JAXBException, ParsingException {

        InputStream inputStream1 = XMLApp.class.getClassLoader().getResourceAsStream(RESOURCES_XML_FILE_PATH);
        InputStream inputStream2 = XMLApp.class.getClassLoader().getResourceAsStream(RESOURCES_XML_FILE_PATH);
        InputStream inputStream3 = XMLApp.class.getClassLoader().getResourceAsStream(RESOURCES_XML_FILE_PATH);

        String xmlFile = XMLApp.class.getClassLoader().getResource(RESOURCES_XML_FILE_PATH).getFile();
        String xsdFile = XMLApp.class.getClassLoader().getResource(RESOURCES_XSD_FILE_PATH).getFile();

        XmlValidator validator = new XmlValidator();
        try {
            validator.validateXMLByXSD(new File(xmlFile), new File(xsdFile));
        } catch (ValidationException e) {
            log.error("{} file is not valid! {}", xmlFile, e);
            throw new ValidationException(MessageFormat.format(
                    "{0} file is not valid! {1}", xmlFile, e));
        }

        SaxEntityParser saxParser = new SaxEntityParser();
        StaxEntityParser staxParser = new StaxEntityParser();
        DomEntityParser domParser = new DomEntityParser();

        Library library1;
        Library library2;
        Library library3;

        try {
            library1 = saxParser.parse(inputStream1);
            library2 = staxParser.parse(inputStream2);
            library3 = domParser.parse(inputStream3);
        } catch (ParsingException e) {
            log.error("The error in the method 'main()' of class XMLApp: {}", e);
            throw new ParsingException(MessageFormat.format("" +
                    "The error in the method 'main()' of class XMLApp: {0}", e));
        }

        log.info("SaxEntityParser: Library = {}", library1);
        log.info("StaxEntityParser: Library = {}", library2);
        log.info("DomEntityParser: Library = {}", library3);

        XmlWriter writer = new JAXBWriter();
        try {
            writer.writeTo("marshal-library.xml", library1);
        } catch (JAXBException e) {
            log.error("Unable to create XML file from object. {}", e);
            throw new JAXBException(MessageFormat.format("Unable to create XML file from object. {0}", e));
        }
    }
}

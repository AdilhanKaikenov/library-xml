package com.epam.adk.task3.library_xml.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.ValidationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * The XmlValidator class. Created on 05.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class XmlValidator {

    private static final Logger log = LoggerFactory.getLogger(XmlValidator.class);

    public void validateXMLByXSD(File xmlFile, File xsdFile) throws ValidationException {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).
                    newSchema(xsdFile).
                    newValidator().
                    validate(new StreamSource(xmlFile));
            log.debug("{} file is valid!", xmlFile);

        } catch (SAXException | IOException e) {
            log.error("{} file is not valid! {}", xmlFile, e);
            throw new ValidationException(MessageFormat.format(
                    "{0} file is not valid! {1}", xmlFile, e));
        }
    }
}



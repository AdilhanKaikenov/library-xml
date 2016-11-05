package com.epam.adk.task3.library_xml.util;

import com.epam.adk.task3.library_xml.parser.SaxEntityParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.text.MessageFormat;

/**
 * The XmlParserValidator class. Created on 05.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class XmlParserValidator {

    private static final Logger log = LoggerFactory.getLogger(SaxEntityParser.class);

    public void validateXMLByXSD(String xml, String xsd) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).
                    newSchema(new File(xsd)).
                    newValidator().
                    validate(new StreamSource(xml));
            log.debug("{} file is valid!", xml);
        } catch (Exception e) {
            log.error("{} file is not valid!", xml);
            throw new RuntimeException(MessageFormat.format(
                    "{0} file is not valid!", e));
        }
    }
}



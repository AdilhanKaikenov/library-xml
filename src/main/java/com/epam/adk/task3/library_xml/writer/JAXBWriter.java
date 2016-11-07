package com.epam.adk.task3.library_xml.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.MessageFormat;

/**
 * The JAXBWriter class. Created on 06.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class JAXBWriter implements XmlWriter {

    private static final Logger log = LoggerFactory.getLogger(JAXBWriter.class);

    @Override
    public void writeTo(String xmlFile, Object classForTransferring) throws JAXBException {
        File file = new File(xmlFile);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classForTransferring.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(classForTransferring, file);
            jaxbMarshaller.marshal(classForTransferring, System.out);
        } catch (JAXBException e) {
            log.error("Unable to create XML file from object. {}", e);
            throw new JAXBException(MessageFormat.format("Unable to create XML file from object. {0}", e));
        }
    }
}

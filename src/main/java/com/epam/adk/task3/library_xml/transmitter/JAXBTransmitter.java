package com.epam.adk.task3.library_xml.transmitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.MessageFormat;

/**
 * The JAXBTransmitter class. Created on 06.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class JAXBTransmitter implements XmlTransmitter {

    private static final Logger log = LoggerFactory.getLogger(JAXBTransmitter.class);

    @Override
    public void transferTo(String xmlFile, Object classForTransferring) {
        File file = new File(xmlFile);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classForTransferring.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(classForTransferring, file);
            jaxbMarshaller.marshal(classForTransferring, System.out);
        } catch (JAXBException e) {
            log.error("Unable to create XML file from object. {}", e);
            throw new RuntimeException(MessageFormat.format("Unable to create XML file from object. {0}", e));
        }
    }
}

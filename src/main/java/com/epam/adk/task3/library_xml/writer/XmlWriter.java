package com.epam.adk.task3.library_xml.writer;

import javax.xml.bind.JAXBException;

/**
 * The interface XmlWriter. Created on 06.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public interface XmlWriter {

    void writeTo(String xmlFile, Object classForTransferring) throws JAXBException;

}

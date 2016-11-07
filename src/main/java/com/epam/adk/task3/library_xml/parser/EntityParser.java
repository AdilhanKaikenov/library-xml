package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.Library;

import java.io.InputStream;

/**
 * The interface EntityParser. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public interface EntityParser {

    Library parse(InputStream inputStream);

}

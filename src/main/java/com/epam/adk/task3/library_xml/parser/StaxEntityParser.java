package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * The StaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class StaxEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(StaxEntityParser.class);

    @Override
    public <C extends BaseEntity> C parse(InputStream inputStream, Class<C> clazz) {
        return null;
    }

}

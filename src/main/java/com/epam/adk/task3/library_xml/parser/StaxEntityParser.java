package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.BaseEntity;

import java.io.InputStream;
import java.util.Collection;

/**
 * The StaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class StaxEntityParser implements EntityParser {

    @Override
    public <C extends BaseEntity> C parse(InputStream inputStream, Class<C> clazz) {
        return null;
    }

    @Override
    public <T extends Collection<C>, C extends BaseEntity> T parse(InputStream inputStream, Class<T> collectionClass, Class<C> clazz) {
        return null;
    }
}

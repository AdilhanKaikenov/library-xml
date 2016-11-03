package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.BaseEntity;

import java.io.InputStream;

/**
 * The interface EntityParser. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public interface EntityParser {

    <C extends BaseEntity> C parse(InputStream inputStream, Class<C> clazz);

//    <T extends Collection<C>, C extends BaseEntity> T parse(InputStream inputStream, Class<T> collectionClass, Class<C> clazz);
}

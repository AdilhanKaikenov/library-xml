package com.epam.adk.task3.library_xml.util;

import com.epam.adk.task3.library_xml.entity.Author;
import com.epam.adk.task3.library_xml.entity.Authors;
import com.epam.adk.task3.library_xml.entity.Book;
import com.epam.adk.task3.library_xml.entity.enums.Genre;
import com.epam.adk.task3.library_xml.entity.enums.Language;
import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Year;

/**
 * The ElementsContentInitialiser class. Created on 05.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class ElementsContentInitialiser {

    private static final Logger log = LoggerFactory.getLogger(ElementsContentInitialiser.class);

    //TODO:
    public static void universalInitialize(ElementEnum elementEnum, Book book, Authors authors, String content) {
        log.trace("Entering ElementsContentInitialiser class, universalInitialize( Argument: elementEnum = {}) method", elementEnum);

        if (elementEnum != null) {
        String element = elementEnum.getValue();

            Class<? extends Book> bookClass = book.getClass();
            authors = new Authors();
            Class<? extends Authors> authorsClass = authors.getClass();

            Field[] bookClassFields = bookClass.getDeclaredFields();
            Method[] bookClassMethods = bookClass.getMethods();

            Field[] authorsClassFields = authorsClass.getDeclaredFields();
            Method[] authorsClassMethods = authorsClass.getMethods();

            Author author = null;

            try {
                for (Field field : bookClassFields) {
                    for (Method method : bookClassMethods) {
                        if (field.getName().equals(element)
                                && method.getName().toLowerCase().contains(field.getName())
                                && method.getName().contains("set")) {
                            if (field.getName().equals("authors")) {

                                for (Field authorsField : authorsClassFields) {
                                    for (Method authorsMethod : authorsClassMethods) {
                                        if (authorsField.getName().equals(element)
                                                && authorsMethod.getName().toLowerCase().contains(authorsField.getName())
                                                && authorsMethod.getName().contains("set")) {
                                            author = new Author();
                                            author.setAuthor(content);
                                            authors.add(author);
                                        }
                                    }
                                }
                            }
                            method.invoke(book, content); // TODO: String content --> IllegalArgumentException
                        }
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("");
            }
        }
        log.trace("ElementsContentInitialiser class, universalInitialize() method finished!");
    }

    /**
     * The method for initializing entities on the current element (tag).
     *
     * @param elementEnum element enumeration.
     * @param book        Book instance.
     * @param authors     Authors instance.
     * @param content     tag text.
     */
    public static void initialize(ElementEnum elementEnum, Book book, Authors authors, String content) {
        log.trace("Entering ElementsContentInitialiser class, initialize( Argument: elementEnum = {}) method", elementEnum);
        if (elementEnum != null && book != null) {
            switch (elementEnum) {
                case ISBN:
                    book.setIsbn(content);
                    break;
                case TITLE:
                    book.setTitle(content);
                    break;
                case GENRE:
                    book.setGenre(Genre.from(content));
                    break;
                case AUTHOR:
                    Author author = new Author();
                    author.setAuthor(content);
                    authors.add(author);
                    break;
                case NUMBER_OF_PUBLISHING:
                    book.setNumberOfPages(Integer.parseInt(content));
                    break;
                case YEAR_OF_PUBLISHING:
                    book.setYearOfPublishing(Year.parse(content));
                    break;
                case LANGUAGE:
                    book.setLanguage(Language.from(content));
                    break;
            }
        }
        log.trace("ElementsContentInitialiser class, initialize() method finished!");
    }
}

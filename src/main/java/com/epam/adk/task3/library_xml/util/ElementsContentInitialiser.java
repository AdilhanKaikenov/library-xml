package com.epam.adk.task3.library_xml.util;

import com.epam.adk.task3.library_xml.entity.Authors;
import com.epam.adk.task3.library_xml.entity.Book;
import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Year;

/**
 * The ElementsContentInitialiser class. Created on 05.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class ElementsContentInitialiser {

    private static final Logger log = LoggerFactory.getLogger(ElementsContentInitialiser.class);

        /**
     * The method for initializing entities on the current element (tag).
     *
     * @param elementEnum element enumeration.
     * @param book        Book instance.
     * @param authors     Authors instance.
     * @param content     tag text.
     */
    public static void initialize(ElementEnum elementEnum, Library library, Book book, Authors authors, String content) {
        log.trace("Entering ElementsContentInitialiser class, initialize( Argument: elementEnum = {}) method", elementEnum);
        if (elementEnum != null) {
            switch (elementEnum) {
                case ID:
                    library.setId(content);
                    break;
                case NAME:
                    library.setName(content);
                    break;
                case ADDRESS:
                    library.setAddress(content);
                    break;
                case ISBN:
                    book.setIsbn(content);
                    break;
                case TITLE:
                    book.setTitle(content);
                    break;
                case GENRE:
                    book.setGenre(Book.Genre.from(content));
                    break;
                case AUTHOR:
                    authors.add(content);
                    break;
                case NUMBER_OF_PUBLISHING:
                    book.setNumberOfPages(Integer.parseInt(content));
                    break;
                case YEAR_OF_PUBLISHING:
                    book.setYearOfPublishing(Year.parse(content));
                    break;
                case LANGUAGE:
                    book.setLanguage(Book.Language.from(content));
                    break;
            }
        }
        log.trace("ElementsContentInitialiser class, initialize() method finished!");
    }
}

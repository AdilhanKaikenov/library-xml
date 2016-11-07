package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.Authors;
import com.epam.adk.task3.library_xml.entity.Book;
import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.exception.ParsingException;
import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;
import com.epam.adk.task3.library_xml.util.ElementsContentInitialiser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The StaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class StaxEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(StaxEntityParser.class);

    private StringBuilder content = new StringBuilder();
    private Library library;
    private List<Book> bookList;
    private Book book;
    private Authors authors;

    public StaxEntityParser() {
        bookList = new ArrayList<>();
    }

    @Override
    public Library parse(InputStream inputStream) throws ParsingException {
        log.debug("Entering StaxEntityParser class, parse() ");
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try (InputStream is = inputStream) {

            XMLStreamReader reader = null;
            try {
                reader = factory.createXMLStreamReader(is);
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
            process(reader);

            log.debug("StaxEntityParser the parse method executed!");

        } catch (IOException | XMLStreamException e) {
            log.error("The error in the method 'parse()' of class StaxEntityParser: {}", e);
            throw new ParsingException(MessageFormat.format(
                    "The error in the method 'parse()' of class StaxEntityParser: {0}", e));
        }
        return library;
    }

    /**
     * The method of controlling the flow of choice.
     *
     * @param reader XMLStreamReader
     * @throws XMLStreamException
     */
    private void process(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {

            int event = reader.next();

            switch (event) {

                case XMLStreamConstants.START_ELEMENT:
                    content.setLength(0);
                    String startElementName = reader.getLocalName();
                    log.trace("START_ELEMENT event, element = {}", startElementName);
                    ElementEnum elementEnum = ElementEnum.from(startElementName);
                    switch (elementEnum) {
                        case LIBRARY:
                            library = new Library();
                            break;
                        case BOOK:
                            book = new Book();
                            break;
                        case AUTHORS:
                            authors = new Authors();
                            break;
                    }
                    log.trace("START_ELEMENT event, elementEnum = {}", elementEnum);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    elementEnum = ElementEnum.from(endElementName);
                    log.trace("END_ELEMENT event, element = {}", elementEnum);
                    switch (elementEnum) {
                        case LIBRARY:
                            library.setBooks(bookList);
                            break;
                        case BOOK:
                            bookList.add(book);
                            break;
                        case AUTHORS:
                            book.setAuthors(authors);
                            break;
                    }
                    ElementsContentInitialiser.initialize(elementEnum, book, authors, content.toString());
                    break;

                case XMLStreamConstants.CHARACTERS:
                    content.append(reader.getText());
                    break;
            }
        }
    }
}

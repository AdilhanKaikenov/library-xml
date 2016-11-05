package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.Author;
import com.epam.adk.task3.library_xml.entity.Authors;
import com.epam.adk.task3.library_xml.entity.Book;
import com.epam.adk.task3.library_xml.entity.Library;
import com.epam.adk.task3.library_xml.entity.enums.Genre;
import com.epam.adk.task3.library_xml.entity.enums.Language;
import com.epam.adk.task3.library_xml.parser.enums.ElementEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.Year;

/**
 * The StaxEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class StaxEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(StaxEntityParser.class);

    private StringBuilder content = new StringBuilder();
    private Library library;
    private Book book;
    private Authors authors;

    @Override
    public Library parse(String xmlFilePath) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try (InputStream is = StaxEntityParser.class.getClassLoader().getResourceAsStream(xmlFilePath)) {
            XMLStreamReader reader = null;
            try {
                reader = factory.createXMLStreamReader(is);
            } finally {
                if (reader != null){
                    reader.close();
                }
            }
            process(reader);
            log.debug("StaxEntityParser the parse method executed!");
        } catch (Exception e) {
            log.error("The error in the method 'parse()' of class StaxEntityParser: {}", e);
            throw new RuntimeException(MessageFormat.format(
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
                        case AUTHORS:
                            book.setAuthors(authors);
                            break;
                        case BOOK:
                            library.add(book);
                            break;
                    }
                    saveContent(endElementName, content.toString());
                    break;
                case XMLStreamConstants.CHARACTERS:
                    content.append(reader.getText());
                    break;
            }
        }
    }

    /**
     * The method for determining elements (tags) and save.
     *
     * @param elementName current element name.
     * @param content     content of the element.
     */
    private void saveContent(String elementName, String content) {
        ElementEnum element = ElementEnum.from(elementName);
        switch (element) {
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
                author.setName(content);
                authors.add(author);
                break;
            case NUMBER_OF_PUBLISHING:
                book.setNumberOfPages(Integer.parseInt(content));
                break;
            case YEAR_OF_PUBLISHING:
                book.setYear(Year.parse(content));
                break;
            case LANGUAGE:
                book.setLanguage(Language.from(content));
                break;
        }
    }
}

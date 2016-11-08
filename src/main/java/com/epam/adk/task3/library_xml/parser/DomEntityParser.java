package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.*;
import com.epam.adk.task3.library_xml.exception.ParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * The DomEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class DomEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(DomEntityParser.class);

    private Library library;

    public DomEntityParser() {
        library = new Library();
    }

    @Override
    public Library parse(InputStream inputStream) throws ParsingException {
        log.debug("Entering DomEntityParser class, parse() ");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try (InputStream is = inputStream) {

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);

            Element root = document.getDocumentElement();
            log.debug("Root element tag name = {}", root.getTagName());

            buildLibrary(root);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            log.error("The error in the method 'parse()' of class DomEntityParser: {}", e);
            throw new ParsingException(MessageFormat.format("" +
                    "The error in the method 'parse()' of class DomEntityParser: {0}", e));
        }
        return library;
    }

    private void buildLibrary(Element root){
        library.setId(root.getElementsByTagName("id").item(0).getTextContent());
        library.setName(root.getElementsByTagName("name").item(0).getTextContent());
        library.setAddress(root.getElementsByTagName("address").item(0).getTextContent());
        List<Book> books = buildBook(root);
        library.setBooks(books);
    }

    private List<Book> buildBook(Element root) {

        List<Book> books = new ArrayList<>();

        NodeList booksNodes = root.getElementsByTagName("book");

        for (int i = 0; i < booksNodes.getLength(); i++) {
            Element bookElement = (Element) booksNodes.item(i);

            Book book = new Book();
            book.setIsbn(bookElement.getElementsByTagName("isbn").item(0).getTextContent());
            book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
            book.setGenre(Book.Genre.from(bookElement.getElementsByTagName("genre").item(0).getTextContent()));

            book.setAuthors(buildAuthors(bookElement));

            book.setNumberOfPages(Integer.parseInt(bookElement.getElementsByTagName("numberOfPages").item(0).getTextContent()));
            book.setYearOfPublishing(Year.parse(bookElement.getElementsByTagName("yearOfPublishing").item(0).getTextContent()));
            book.setLanguage(Book.Language.from(bookElement.getElementsByTagName("language").item(0).getTextContent()));

            books.add(book);
        }
        return books;
    }

    private Authors buildAuthors(Element bookElement) {

        Authors authors = new Authors();

        NodeList authorsNodes = bookElement.getElementsByTagName("author");
        for (int i = 0; i < authorsNodes.getLength(); i++) {

            Element authorElement = (Element) authorsNodes.item(i);

            authors.add(authorElement.getTextContent());
        }
        return authors;
    }
}

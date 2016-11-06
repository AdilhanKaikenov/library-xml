package com.epam.adk.task3.library_xml.parser;

import com.epam.adk.task3.library_xml.entity.*;
import com.epam.adk.task3.library_xml.entity.enums.Genre;
import com.epam.adk.task3.library_xml.entity.enums.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.Year;

/**
 * The DomEntityParser class. Created on 03.11.2016.
 *
 * @author Kaikenov Adilhan.
 */
public class DomEntityParser implements EntityParser {

    private static final Logger log = LoggerFactory.getLogger(DomEntityParser.class);

    private Library library;
    private Books books;

    public DomEntityParser() {
        library = new Library();
        books = new Books();
    }

    @Override
    public Library parse(String resourcesXMLFilePath) {
        log.debug("Entering DomEntityParser class, parse( Argument: resourcesXMLFilePath = {}) ", resourcesXMLFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try (InputStream is = DomEntityParser.class.getClassLoader().getResourceAsStream(resourcesXMLFilePath)) {

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);

            Element root = document.getDocumentElement();
            log.debug("Root element tag name = {}", root.getTagName());

            bookBuild(root);

            library.setBooks(books);

        } catch (Exception e) {
            log.error("The error in the method 'parse()' of class DomEntityParser: {}", e);
            throw new RuntimeException(MessageFormat.format("" +
                    "The error in the method 'parse()' of class DomEntityParser: {0}", e));
        }
        return library;
    }

    private void bookBuild(Element root) {

        NodeList booksNodes = root.getElementsByTagName("book");

        for (int i = 0; i < booksNodes.getLength(); i++) {
            Element bookElement = (Element) booksNodes.item(i);

            Book book = new Book();
            book.setIsbn(bookElement.getElementsByTagName("isbn").item(0).getTextContent());
            book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
            book.setGenre(Genre.from(bookElement.getElementsByTagName("genre").item(0).getTextContent()));

            book.setAuthors(authorsBuild(bookElement));

            book.setNumberOfPages(Integer.parseInt(bookElement.getElementsByTagName("numberOfPages").item(0).getTextContent()));
            book.setYearOfPublishing(Year.parse(bookElement.getElementsByTagName("yearOfPublishing").item(0).getTextContent()));
            book.setLanguage(Language.from(bookElement.getElementsByTagName("language").item(0).getTextContent()));

            books.add(book);
        }
    }

    private Authors authorsBuild(Element bookElement) {

        Authors authors = new Authors();

        NodeList authorsNodes = bookElement.getElementsByTagName("author");
        for (int i = 0; i < authorsNodes.getLength(); i++) {

            Author author = new Author();
            Element authorElement = (Element) authorsNodes.item(i);
            author.setAuthor(authorElement.getTextContent());

            authors.add(author);
        }
        return authors;
    }
}

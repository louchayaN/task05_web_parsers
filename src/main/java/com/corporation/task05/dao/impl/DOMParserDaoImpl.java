package com.corporation.task05.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.corporation.task05.dao.XmlParserDao;
import com.corporation.task05.dao.exception.IncorrectDateFormatDaoException;
import com.corporation.task05.dao.exception.XmlParsingDaoException;
import com.corporation.task05.dao.util.DateUtil;
import com.corporation.task05.entity.Book;

public class DOMParserDaoImpl implements XmlParserDao {

    @Override
    public List<Book> getBookCatalog() throws XmlParsingDaoException, IncorrectDateFormatDaoException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("/books.xml");
            Document document = builder.parse(inputStream);
            Element root = document.getDocumentElement();

            List<Book> bookCatalog = new ArrayList<>();
            NodeList bookNodes = root.getElementsByTagName("book");
            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElement = (Element) bookNodes.item(i);
                Book book = formBook(bookElement);
                bookCatalog.add(book);
            }
            return bookCatalog;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new XmlParsingDaoException("Exception occurred during reading the xml file", e);
        }
    }

    private String getSingleChildContent(Element element, String childName) {
        Element child = getSingleChild(element, childName);
        String childContent = child.getTextContent().trim();
        return childContent;
    }

    private Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }

    private Book formBook(Element bookElement) throws IncorrectDateFormatDaoException {
        Book book = new Book();
        int bookId = Integer.parseInt(bookElement.getAttribute("id"));
        book.setId(bookId);
        String author = getSingleChildContent(bookElement, "author");
        book.setAuthor(author);
        String title = getSingleChildContent(bookElement, "title");
        book.setTitle(title);
        String genre = getSingleChildContent(bookElement, "genre");
        book.setGenre(genre);
        String price = getSingleChildContent(bookElement, "price");
        book.setPrice(Double.parseDouble(price));
        String date = getSingleChildContent(bookElement, "publish-date");
        try {
            book.setPublishDate(DateUtil.parseDate(date));
        } catch (ParseException e) {
            throw new IncorrectDateFormatDaoException(
                    "Date format for section 'date' in parsing xml file was choosed incorrectly", e);
        }
        String description = getSingleChildContent(bookElement, "description");
        book.setDescription(description);
        return book;
    }

}

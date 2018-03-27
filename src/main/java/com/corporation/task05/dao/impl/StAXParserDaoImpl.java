package com.corporation.task05.dao.impl;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.corporation.task05.dao.XmlParserDao;
import com.corporation.task05.dao.exception.IncorrectDateFormatDaoException;
import com.corporation.task05.dao.exception.XmlParsingDaoException;
import com.corporation.task05.dao.util.DateUtil;
import com.corporation.task05.entity.Book;
import com.corporation.task05.entity.BookTagName;

public class StAXParserDaoImpl implements XmlParserDao {

    private List<Book> bookCatalog;
    private Book book;
    private BookTagName tagName;
    private XMLStreamReader reader;

    @Override
    public List<Book> getBookCatalog() throws XmlParsingDaoException, IncorrectDateFormatDaoException {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            inputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("/books.xml");
            reader = inputFactory.createXMLStreamReader(inputStream);
            List<Book> bookCatalog = parseAllXmlFile(reader);
            return bookCatalog;

        } catch (XMLStreamException e) {
            throw new XmlParsingDaoException("Exception occurred during reading the xml file", e);
        }
    }

    private List<Book> parseAllXmlFile(XMLStreamReader reader)
            throws XMLStreamException, IncorrectDateFormatDaoException {
        bookCatalog = new ArrayList<>();
        while (reader.hasNext()) {
            int parseEventType = reader.next();
            switch (parseEventType) {
            case XMLStreamConstants.START_ELEMENT:
                getTagName();
                analizeStartTag();
                break;
            case XMLStreamConstants.CHARACTERS:
                String tagBody = reader.getText().trim();
                if (! tagBody.isEmpty()) {
                    analizeTagBody(tagBody);
                }
                break;
            case XMLStreamConstants.END_ELEMENT:
                getTagName();
                analizeEndTag();
                break;
            }
        }
        return bookCatalog;
    }

    private void getTagName() {
        tagName = BookTagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
    }

    private void analizeStartTag() {
        if (BookTagName.BOOK.equals(tagName)) {
            book = new Book();
            int bookId = Integer.parseInt(reader.getAttributeValue(null, "id"));
            book.setId(bookId);
        }
    }

    private void analizeTagBody(String tagBody) throws IncorrectDateFormatDaoException {
        switch (tagName) {
        case AUTHOR:
            book.setAuthor(tagBody);
            break;
        case TITLE:
            book.setTitle(tagBody);
            break;
        case GENRE:
            book.setGenre(tagBody);
            break;
        case PRICE:
            book.setPrice(Double.parseDouble(tagBody));
            break;
        case PUBLISH_DATE:
            try {
                Date date = DateUtil.parseDate(tagBody);
                book.setPublishDate(date);
            } catch (ParseException e) {
                throw new IncorrectDateFormatDaoException(
                        "Date format for section 'date' in parsing xml file was choosed incorrectly", e);
            }
            break;
        case DESCRIPTION:
            book.setDescription(tagBody);
            break;
        default:
            break;
        }
    }

    private void analizeEndTag() {
        if (BookTagName.BOOK.equals(tagName)) {
            bookCatalog.add(book);
        }
    }

}

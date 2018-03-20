package com.corporation.task05.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.corporation.task05.dao.util.DateUtil;
import com.corporation.task05.entity.Book;
import com.corporation.task05.entity.BookTagName;

public class BookHandler extends DefaultHandler {

    private List<Book> bookCatalog = new ArrayList<>();
    private StringBuilder tagBody = new StringBuilder();
    private Book book;

    public List<Book> getBookCatalog() {
        return bookCatalog;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagBody.setLength(0);
        if ("book".equals(qName)) {
            book = new Book();
            int bookId = Integer.parseInt(attributes.getValue("id"));
            book.setId(bookId);
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) throws SAXException {
        tagBody.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        BookTagName tagName = BookTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch (tagName) {
        case AUTHOR:
            book.setAuthor(tagBody.toString());
            break;
        case TITLE:
            book.setTitle(tagBody.toString());
            break;
        case GENRE:
            book.setGenre(tagBody.toString());
            break;
        case PRICE:
            book.setPrice(Double.parseDouble(tagBody.toString()));
            break;
        case PUBLISH_DATE:
            try {
                Date date = DateUtil.parseDate(tagBody.toString());
                book.setPublishDate(date);
            } catch (ParseException e) {
                throw new SAXException("Date format for section 'date' in parsing xml file was choosed incorrectly", e);
            }
            break;
        case DESCRIPTION:
            book.setDescription(tagBody.toString());
            break;
        case BOOK:
            bookCatalog.add(book);
            book = null;
            break;
        case CATALOG:
            break;
        }
    }

}

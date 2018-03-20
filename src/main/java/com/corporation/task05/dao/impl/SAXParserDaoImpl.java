package com.corporation.task05.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.corporation.task05.dao.XmlParserDao;
import com.corporation.task05.dao.exception.XmlParsingDaoException;
import com.corporation.task05.entity.Book;

public class SAXParserDaoImpl implements XmlParserDao {

    @Override
    public List<Book> getBookCatalog() throws XmlParsingDaoException {
        try {
            SAXParser saxParser = SAXParserFactory.newDefaultInstance().newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            BookHandler handler = new BookHandler();
            reader.setContentHandler(handler);

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("/books.xml");
            reader.parse(new InputSource(inputStream));

            List<Book> bookCatalog = handler.getBookCatalog();
            return bookCatalog;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new XmlParsingDaoException("Exception occurred during reading the xml file", e);
        }
    }

}

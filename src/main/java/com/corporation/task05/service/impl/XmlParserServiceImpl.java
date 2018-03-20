package com.corporation.task05.service.impl;

import java.util.List;

import com.corporation.service.exception.IncorrectDateFormatServiceException;
import com.corporation.service.exception.UnsupportedParserTypeServiceException;
import com.corporation.service.exception.XmlParsingServiceException;
import com.corporation.task05.dao.DAOFactory;
import com.corporation.task05.dao.XmlParserDao;
import com.corporation.task05.dao.exception.IncorrectDateFormatDaoException;
import com.corporation.task05.dao.exception.UnsupportedParserTypeDaoException;
import com.corporation.task05.dao.exception.XmlParsingDaoException;
import com.corporation.task05.entity.Book;
import com.corporation.task05.entity.BooksView;
import com.corporation.task05.service.XmlParserService;

public class XmlParserServiceImpl implements XmlParserService {

    private static final int BOOKS_PER_PAGE = 2;

    private String parserType;

    public XmlParserServiceImpl(String parserType) {
        this.parserType = parserType;
    }

    @Override
    public BooksView getBooksForPage(int currentPage) throws XmlParsingServiceException,
            IncorrectDateFormatServiceException, UnsupportedParserTypeServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            XmlParserDao parser = daoFactory.getParser(parserType);
            List<Book> bookCatalog = parser.getBookCatalog();

            int totalPageCount = (int) Math.ceil((double) bookCatalog.size() / BOOKS_PER_PAGE);
            int startIndex = (currentPage - 1) * BOOKS_PER_PAGE;
            int endIndex = startIndex + (BOOKS_PER_PAGE - 1);
            List<Book> currentViewBooks = bookCatalog.subList(startIndex, endIndex + 1);

            BooksView booksView = new BooksView();
            booksView.setCurrentPage(currentPage);
            booksView.setTotalPageCount(totalPageCount);
            booksView.setBooksPerPage(BOOKS_PER_PAGE);
            booksView.setCurrentViewBooks(currentViewBooks);
            return booksView;

        } catch (XmlParsingDaoException e) {
            throw new XmlParsingServiceException("Exception occurred during reading the xml file", e);
        } catch (IncorrectDateFormatDaoException e) {
            throw new IncorrectDateFormatServiceException(
                    "Date format for section 'date' in parsing xml file was choosed incorrectly", e);
        } catch (UnsupportedParserTypeDaoException e) {
            throw new UnsupportedParserTypeServiceException("Attempt to create xml parser of the unsupported type", e);
        }
    }

}

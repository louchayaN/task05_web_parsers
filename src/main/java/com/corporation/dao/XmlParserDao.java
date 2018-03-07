package com.corporation.dao;

import java.util.List;

import com.corporation.dao.exception.IncorrectDateFormatDaoException;
import com.corporation.dao.exception.XmlParsingDaoException;
import com.corporation.entity.Book;

public interface XmlParserDao {

    List<Book> getBookCatalog() throws XmlParsingDaoException, IncorrectDateFormatDaoException;

}

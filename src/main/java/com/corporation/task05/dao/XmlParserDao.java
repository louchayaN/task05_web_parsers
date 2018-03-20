package com.corporation.task05.dao;

import java.util.List;

import com.corporation.task05.dao.exception.IncorrectDateFormatDaoException;
import com.corporation.task05.dao.exception.XmlParsingDaoException;
import com.corporation.task05.entity.Book;

public interface XmlParserDao {

    List<Book> getBookCatalog() throws XmlParsingDaoException, IncorrectDateFormatDaoException;

}

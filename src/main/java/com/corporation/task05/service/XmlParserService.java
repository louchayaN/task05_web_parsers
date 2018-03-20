package com.corporation.task05.service;

import com.corporation.service.exception.IncorrectDateFormatServiceException;
import com.corporation.service.exception.UnsupportedParserTypeServiceException;
import com.corporation.service.exception.XmlParsingServiceException;
import com.corporation.task05.entity.BooksView;

public interface XmlParserService {

    BooksView getBooksForPage(int page) throws XmlParsingServiceException, IncorrectDateFormatServiceException,
            UnsupportedParserTypeServiceException;

}

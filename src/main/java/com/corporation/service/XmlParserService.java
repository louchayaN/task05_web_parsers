package com.corporation.service;

import com.corporation.entity.BooksView;
import com.corporation.service.exception.IncorrectDateFormatServiceException;
import com.corporation.service.exception.UnsupportedParserTypeServiceException;
import com.corporation.service.exception.XmlParsingServiceException;

public interface XmlParserService {

    BooksView getBooksForPage(int page) throws XmlParsingServiceException, IncorrectDateFormatServiceException,
            UnsupportedParserTypeServiceException;

}

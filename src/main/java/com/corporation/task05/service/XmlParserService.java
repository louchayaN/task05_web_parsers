package com.corporation.task05.service;

import com.corporation.task05.entity.BooksView;
import com.corporation.task05.service.exception.IncorrectDateFormatServiceException;
import com.corporation.task05.service.exception.UnsupportedParserTypeServiceException;
import com.corporation.task05.service.exception.XmlParsingServiceException;

public interface XmlParserService {

    BooksView getBooksForPage(int page, String parserType) throws XmlParsingServiceException,
            IncorrectDateFormatServiceException, UnsupportedParserTypeServiceException;

}

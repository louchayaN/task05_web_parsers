package com.corporation.task05.dao;

import com.corporation.task05.dao.exception.UnsupportedParserTypeDaoException;
import com.corporation.task05.dao.impl.DOMParserDaoImpl;
import com.corporation.task05.dao.impl.SAXParserDaoImpl;
import com.corporation.task05.dao.impl.StAXParserDaoImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public XmlParserDao getParser(String parserType) throws UnsupportedParserTypeDaoException {
        switch (parserType) {
        case "SAX":
            return new SAXParserDaoImpl();
        case "StAX":
            return new StAXParserDaoImpl();
        case "DOM":
            return new DOMParserDaoImpl();
        default:
            throw new UnsupportedParserTypeDaoException("An attempt to create xml parser of the unsupported type");
        }
    }

}

package com.corporation.task05.dao;

import java.util.HashMap;
import java.util.Map;

import com.corporation.task05.dao.exception.UnsupportedParserTypeDaoException;
import com.corporation.task05.dao.impl.DOMParserDaoImpl;
import com.corporation.task05.dao.impl.SAXParserDaoImpl;
import com.corporation.task05.dao.impl.StAXParserDaoImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private static final Map<String, XmlParserDao> parsers = new HashMap<>();

    static {
        parsers.put("SAX", new SAXParserDaoImpl());
        parsers.put("StAX", new StAXParserDaoImpl());
        parsers.put("DOM", new DOMParserDaoImpl());
    }

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public XmlParserDao getParser(String parserType) throws UnsupportedParserTypeDaoException {
        XmlParserDao xmlParserDao = parsers.get(parserType);
        if (xmlParserDao == null) {
            throw new UnsupportedParserTypeDaoException("An attempt to create xml parser of the unsupported type");
        }
        return xmlParserDao;
    }

}

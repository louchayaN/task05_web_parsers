package com.corporation.dao;

import com.corporation.dao.impl.DOMParserDaoImpl;
import com.corporation.dao.impl.SAXParserDaoImpl;
import com.corporation.dao.impl.StAXParserDaoImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public XmlParserDao getParser(String parserType) {
        switch (parserType) {
        case "SAX":
            return new SAXParserDaoImpl();
        case "StAX":
            return new StAXParserDaoImpl();
        case "DOM":
            return new DOMParserDaoImpl();
        }
        return null;
    }

}

package com.corporation.service;

import com.corporation.service.impl.XmlParserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public XmlParserService getXmlParserService(String parserType) {
        return new XmlParserServiceImpl(parserType);
    }
}

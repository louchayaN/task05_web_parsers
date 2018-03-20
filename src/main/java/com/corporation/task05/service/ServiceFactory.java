package com.corporation.task05.service;

import com.corporation.task05.service.impl.XmlParserServiceImpl;

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

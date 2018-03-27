package com.corporation.task05.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.corporation.task05.controller.command.Command;
import com.corporation.task05.entity.BooksView;
import com.corporation.task05.service.ServiceFactory;
import com.corporation.task05.service.XmlParserService;
import com.corporation.task05.service.exception.IncorrectDateFormatServiceException;
import com.corporation.task05.service.exception.UnsupportedParserTypeServiceException;
import com.corporation.task05.service.exception.XmlParsingServiceException;

public class XmlParsersCommand implements Command {

    private static final String BOOKS_VIEW_PAGE = "/WEB-INF/jsp/book.jsp";
    private static final String ERROR_PAGE_404 = "/WEB-INF/jsp/error-page-404.jsp";
    private static final String ERROR_PAGE_503 = "/WEB-INF/jsp/error-page-503.jsp";

    private static final Logger LOGGER = LogManager.getLogger(XmlParsersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("parserType") != null) {
            session.setAttribute("parserType", request.getParameter("parserType"));
        }

        String parserType = (String) session.getAttribute("parserType");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        String path;
        try {
            XmlParserService parserService = ServiceFactory.getInstance().getXmlParserService();
            BooksView booksView = parserService.getBooksForPage(currentPage, parserType);
            session.setAttribute("booksView", booksView);
            path = BOOKS_VIEW_PAGE;
        } catch (IncorrectDateFormatServiceException e) {
            LOGGER.error("Error during getting books' date. The date field of Book ojects will be null.", e);
            path = BOOKS_VIEW_PAGE;
        } catch (UnsupportedParserTypeServiceException e) {
            LOGGER.fatal("Incorrect parser type was choosed.", e);
            path = ERROR_PAGE_404;
        } catch (XmlParsingServiceException e) {
            LOGGER.fatal("Xml file with nessery date haven't been read correctly.", e);
            path = ERROR_PAGE_503;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

}

package com.corporation.task05.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.corporation.task05.entity.BooksView;
import com.corporation.task05.service.ServiceFactory;
import com.corporation.task05.service.XmlParserService;
import com.corporation.task05.service.exception.IncorrectDateFormatServiceException;
import com.corporation.task05.service.exception.UnsupportedParserTypeServiceException;
import com.corporation.task05.service.exception.XmlParsingServiceException;

public class XmlParsersController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String BOOKS_VIEW_PAGE = "/WEB-INF/jsp/book.jsp";
    private static final String ERROR_PAGE_404 = "/WEB-INF/jsp/error-page-404.jsp";
    private static final String ERROR_PAGE_503 = "/WEB-INF/jsp/error-page-503.jsp";

    private static final Logger LOGGER = LogManager.getLogger(XmlParsersController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("parserType") != null) {
            session.setAttribute("parserType", request.getParameter("parserType"));
        }

        String parserType = (String) session.getAttribute("parserType");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            XmlParserService parser = factory.getXmlParserService(parserType);
            BooksView booksView = parser.getBooksForPage(currentPage);
            session.setAttribute("booksView", booksView);

            RequestDispatcher dispatcher = request.getRequestDispatcher(BOOKS_VIEW_PAGE);
            dispatcher.forward(request, response);

        } catch (IncorrectDateFormatServiceException e) {
            LOGGER.warn(
                    "Exception occurred during parsing date. Date format for section 'date' in parsing xml file was choosed incorrectly. "
                            + "The date field of Book ojects will be null");
        } catch (UnsupportedParserTypeServiceException e) {
            LOGGER.error("Incorrect parser type was choosed");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_404);
            dispatcher.forward(request, response);
        } catch (XmlParsingServiceException e) {
            LOGGER.error("Xml file with nessery date haven't been read correctly");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_503);
            dispatcher.forward(request, response);
        }
    }

}

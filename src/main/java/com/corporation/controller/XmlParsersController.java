package com.corporation.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.corporation.entity.BooksView;
import com.corporation.service.ServiceFactory;
import com.corporation.service.XmlParserService;
import com.corporation.service.exception.IncorrectDateFormatServiceException;
import com.corporation.service.exception.XmlParsingServiceException;

public class XmlParsersController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String VIEW_PAGE = "/WEB-INF/page/book.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/page/error-page.jsp";

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

            RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PAGE);
            dispatcher.forward(request, response);

        } catch (IncorrectDateFormatServiceException e) {
            LOGGER.warn(
                    "Exception occurred during parsing date. Date format for section 'date' in parsing xml file was choosed incorrectly. "
                            + "The date field of Book ojects will be null");
        } catch (XmlParsingServiceException e) {
            LOGGER.error("Xml file with nessery date haven't been read correctly");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }

}

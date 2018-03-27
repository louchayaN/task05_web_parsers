package com.corporation.task05.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corporation.task05.controller.command.Command;

public class GoToCommand implements Command {

    private static final String VIEW_PAGES_PATH = "/WEB-INF/jsp/";

    private final String page;

    public GoToCommand(String page) {
        this.page = page;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PAGES_PATH + page);
        dispatcher.forward(request, response);

    }

}

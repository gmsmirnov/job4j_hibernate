package ru.job4j.gsmirnov.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Tasks controller. Redirects the request to the index page.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 08/09/2019
 */
public class TasksController extends HttpServlet {
    /**
     * Redirects the request for '/tasks' to the index.html page.
     *
     * @param req HTTP request for '/tasks'.
     * @param resp HTTP response.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.html").forward(req, resp);
    }
}
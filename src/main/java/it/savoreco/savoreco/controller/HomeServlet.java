package it.savoreco.savoreco.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@WebServlet(
        name = "homeServlet",
        displayName = "Savoreco - Home",
        description = "Savoreco home page",
        value = "/home"
)
public class HomeServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(HomeServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.warn("Cannot forward to index.jsp", e);
        }

    }

}
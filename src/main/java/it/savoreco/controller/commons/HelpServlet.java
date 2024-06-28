package it.savoreco.controller.commons;


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
        name = "helpServlet",
        displayName = "Savoreco - Help",
        description = "Savoreco help page",
        value = "/help"
)
public class HelpServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(HelpServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/help.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to help.jsp", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

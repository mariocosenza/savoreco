package it.savoreco.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(value = "/exit")
public class InvalidateServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InvalidateServlet.class);
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        if(req.getSession(false) != null) {
            req.getSession().invalidate();
        }
        try {
            resp.sendRedirect("/home");
        } catch (IOException e) {
            logger.warn("Error sending redirect to invalidate session", e);
        }
    }
}

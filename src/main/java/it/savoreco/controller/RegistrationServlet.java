package it.savoreco.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@WebInitParam(
        name = "usernamePattern",
        value = ""
)
@WebServlet(
        name = "registrationPage",
        value = "/registration",
        asyncSupported = true
)
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        /*
        Pattern patternUsername = Pattern.compile(getServletContext().getInitParameter("usernamePattern"));
        Matcher matcher = patternUsername.matcher(req.getParameter("username"));
        Pattern patternPassword = Pattern.compile(getServletContext().getInitParameter("passwordPattern"));
        Pattern patternEmail = Pattern.compile(getServletContext().getInitParameter("emailPattern"));
*/
        System.out.println("recived user: ");


        resp.setStatus(HttpServletResponse.SC_ACCEPTED);

    }

    /**
     * TODO add account bing with hibernate
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/registration.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to registration.jsp", e);
        }
    }
}

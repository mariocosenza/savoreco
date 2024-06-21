package it.savoreco.controller;

import it.savoreco.model.entity.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(
        name = "moderatorPageServlet",
        displayName = "Moderator - Home",
        description = "Moderator page",
        value = "/moderatorPage"
)
public class ModeratorPageServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ModeratorPageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/moderator/moderatorPage.jsp");

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query<UserAccount> userQuery = session.createQuery("FROM UserAccount us ", UserAccount.class);
            List<UserAccount> usersList = userQuery.list();

            transaction.commit();

            request.setAttribute("usersList", usersList);

            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to moderatorPage.jsp", e);
        }
    }
}



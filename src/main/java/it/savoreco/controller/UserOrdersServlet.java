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
import java.util.List;

@WebServlet(
        name = "UserOrdersServlet",
        displayName = "UserOrders - Home",
        description = "UserOrders page",
        value = "/userOrders"
)
public class UserOrdersServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserOrdersServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/userOrders.jsp");

        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null){
            try {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query<BoughtFood> bFoodQuery = session.createQuery("FROM BoughtFood bf " +
                    "WHERE bf.purchase.user = :user", BoughtFood.class);
            bFoodQuery.setParameter("user", user);
            List<BoughtFood> orders = bFoodQuery.list();

            transaction.commit();

            request.setAttribute("orders", orders);
            request.setAttribute("user", user);

            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to userOrders.jsp", e);
        }
    }
}



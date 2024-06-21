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
        name = "ordersServlet",
        displayName = "Orders - Home",
        description = "Orders page",
        value = "/orders"
)
public class OrdersServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrdersServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/seller/orders.jsp");

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            int id = Integer.parseInt(request.getParameter("id"));

            Query<Restaurant> restaurantQuery = session.createQuery("FROM Restaurant r "
                    +"WHERE r.id = :id", Restaurant.class);
            restaurantQuery.setParameter("id", id);
            Restaurant restaurant = restaurantQuery.getSingleResult();

            Query<BoughtFood> bFoodQuery = session.createQuery("SELECT bf FROM BoughtFood bf " +
                    "WHERE bf.food.restaurant = :restaurant", BoughtFood.class);
            bFoodQuery.setParameter("restaurant", restaurant);
            List<BoughtFood> orders = bFoodQuery.list();

            transaction.commit();

            request.setAttribute("orders", orders);
            request.setAttribute("restaurant", restaurant);

            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to orders.jsp", e);
        }
    }
}



package it.savoreco.controller;

import it.savoreco.model.entity.Food;
import it.savoreco.model.entity.Restaurant;
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
        name = "restaurantServlet",
        displayName = "Restaurant - Home",
        description = "Restaurant page",
        value = "/restaurant"
)
public class RestaurantServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/restaurant.jsp");

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            int aid = Integer.parseInt(request.getParameter("id"));
            System.out.println(aid);

            Query<Restaurant> restaurantQuery = session.createQuery("FROM Restaurant r "
                    +"WHERE r.id = :id", Restaurant.class);
            restaurantQuery.setParameter("id", aid);
            Restaurant restaurant = restaurantQuery.getSingleResult();

            Query<Food> foodQuery = session.createQuery("FROM Food f "
                    +"WHERE f.restaurant = :restaurant AND f.available = true", Food.class);
            foodQuery.setParameter("restaurant", restaurant);
            List<Food> foodList = foodQuery.list();

            transaction.commit();

            request.setAttribute("foodList", foodList);
            request.setAttribute("restaurant", restaurant);

            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to restaurant.jsp", e);
        }
    }
}
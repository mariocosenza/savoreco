package it.savoreco.controller.commons;

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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/restaurant.jsp");

        try {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            int id = Integer.parseInt(req.getParameter("id"));

            Query<Restaurant> restaurantQuery = session.createQuery("FROM Restaurant r "
                    + "WHERE r.id = :id", Restaurant.class);
            restaurantQuery.setParameter("id", id);
            Restaurant restaurant = restaurantQuery.getSingleResult();

            Query<Food> foodQuery = session.createQuery("FROM Food f "
                    + "WHERE f.restaurant = :restaurant AND f.available = true ORDER BY f.category", Food.class);
            foodQuery.setParameter("restaurant", restaurant);
            List<Food> foodList = foodQuery.list();

            transaction.commit();

            if (restaurant.getDeleted()) {
                try {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                } catch (IOException ioException) {
                    logger.warn("Error sending error", ioException);
                }
            }

            req.setAttribute("foodList", foodList);
            req.setAttribute("restaurant", restaurant);

            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to restaurant.jsp", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }
}
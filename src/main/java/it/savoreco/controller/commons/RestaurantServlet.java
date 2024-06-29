package it.savoreco.controller.commons;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import it.savoreco.model.entity.Basket;
import it.savoreco.model.entity.Food;
import it.savoreco.model.entity.Restaurant;
import it.savoreco.model.entity.UserAccount;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
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
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(
        name = "restaurantServlet",
        displayName = "Restaurant - Home",
        description = "Restaurant page",
        value = "/restaurant"
)
public class RestaurantServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantServlet.class);

    private Pattern idMatcher;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        idMatcher = Pattern.compile("^\\d+$");
    }

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
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map;

        try {
            Gson gson = new Gson();
            map = gson.fromJson(req.getReader(), mapType);
        } catch (IOException e) {
            logger.error("Error parsing JSON", e);
            return;
        }

        var foodId = map.get("foodId");

        UserAccount user = (UserAccount) req.getSession().getAttribute("user");
        if (user == null) {
            try {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (idMatcher.matcher(foodId).matches()) {

            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            try {
                Query<Food> foodQuery = session.createQuery("FROM Food f "
                        + "WHERE f.id = :foodId", Food.class);
                foodQuery.setParameter("foodId", foodId.trim());
                Food food = foodQuery.getSingleResult();

                Query<Basket> basketQuery = session.createQuery("FROM Basket b "
                        + "WHERE b.user = :user", Basket.class);
                basketQuery.setParameter("user", user);
                Basket basket = basketQuery.getSingleResult();


                transaction.commit();
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);

            } catch (Exception e) {
                try {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } catch (IOException ioException) {
                    logger.warn("Error sending error", ioException);
                }
            }
        } else {
            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException e) {
                logger.warn("Error sending error", e);
            }
        }
    }
}
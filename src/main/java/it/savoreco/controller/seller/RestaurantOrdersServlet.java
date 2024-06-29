package it.savoreco.controller.seller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
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
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "restaurantOrdersServlet",
        displayName = "RestaurantOrders - Home",
        description = "RestaurantOrders page",
        value = "/seller/restaurantOrders"
)
public class RestaurantOrdersServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantOrdersServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/seller/restaurantOrders.jsp");

        SellerAccount seller = (SellerAccount) request.getSession().getAttribute("seller");

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Restaurant restaurant = seller.getRestaurant();

            Query<BoughtFood> bFoodQuery = session.createQuery("FROM BoughtFood bf " +
                    "WHERE bf.restaurant = :restaurant ORDER BY bf.purchase.time", BoughtFood.class);
            bFoodQuery.setParameter("restaurant", restaurant);
            List<BoughtFood> orders = bFoodQuery.list();

            transaction.commit();

            request.setAttribute("orders", orders);
            request.setAttribute("restaurant", restaurant);

            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to restaurantOrders.jsp", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map;

        try {
            Gson gson = new Gson();
            map = gson.fromJson(request.getReader(), mapType);
        } catch (IOException e) {
            logger.error("Error parsing JSON", e);
            return;
        }

        var id = map.get("id").trim();
        var mode = map.get("mode").trim();

        SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query<Purchase> purchaseQuery = session.createQuery("FROM Purchase p " +
                    "WHERE p.id = :id", Purchase.class);
            purchaseQuery.setParameter("id", id);
            Purchase purchase = purchaseQuery.getSingleResult();

            if(purchase != null) {
                if ((mode.equals("sendRider")) && (purchase.getStatus().equals(Purchase.Statuses.pending))) {
                    purchase.setStatus(Purchase.Statuses.delivering);
                    session.merge(purchase);
                    transaction.commit();
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                    return;
                } else if ((mode.equals("arrived")) && (purchase.getStatus().equals(Purchase.Statuses.delivering))) {
                    purchase.setStatus(Purchase.Statuses.delivered);
                    session.merge(purchase);
                    transaction.commit();
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                    return;
                }
            }

            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException e) {
                logger.warn("Error sending error", e);
            }

        } catch (Exception e) {
            transaction.rollback();
            logger.error("Error updating status", e);
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ioException) {
                logger.warn("Error sending error", ioException);
            }
        }
    }
}



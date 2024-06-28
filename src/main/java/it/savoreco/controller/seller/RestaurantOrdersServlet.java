package it.savoreco.controller.seller;

import it.savoreco.model.entity.BoughtFood;
import it.savoreco.model.entity.Restaurant;
import it.savoreco.model.entity.SellerAccount;
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
}



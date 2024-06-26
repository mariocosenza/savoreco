package it.savoreco.controller;

import it.savoreco.model.entity.Food;
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
import java.math.BigDecimal;
import java.util.List;

@WebServlet(
        name = "sellerRestaurantServlet",
        displayName = "SellerRestaurant - Home",
        description = "SellerRestaurant management page",
        value = "/seller/sellerRestaurant"
)
public class SellerRestaurantServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SellerRestaurantServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/seller/sellerRestaurant.jsp");

        SellerAccount seller = (SellerAccount) request.getSession().getAttribute("seller");

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query<Restaurant> RestaurantQuery = session.createQuery("FROM Restaurant r " +
                    "WHERE r.id = :id", Restaurant.class);
            RestaurantQuery.setParameter("id", seller.getRestaurant().getId());
            Restaurant restaurant = RestaurantQuery.getSingleResult();

            Query<Food> FoodQuery = session.createQuery("FROM Food f " +
                    "WHERE f.restaurant = :restaurant", Food.class);
            FoodQuery.setParameter("restaurant", restaurant);
            List<Food> products = FoodQuery.list();

            Query<BigDecimal> CostQuery = session.createQuery("SELECT SUM(bf.price * bf.quantity) FROM BoughtFood bf " +
                    "WHERE bf.restaurant = :restaurant", BigDecimal.class);
            CostQuery.setParameter("restaurant", restaurant);
            String totalCost = String.format("%.2f", CostQuery.getSingleResult().doubleValue());

            transaction.commit();

            request.setAttribute("products", products);
            request.setAttribute("restaurant", restaurant);
            request.setAttribute("totalCost", totalCost);


            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to sellerRestaurant.jsp", e);
        }
    }
}

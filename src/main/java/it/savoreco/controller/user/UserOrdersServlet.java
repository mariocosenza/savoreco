package it.savoreco.controller.user;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import it.savoreco.model.entity.BoughtFood;
import it.savoreco.model.entity.Purchase;
import it.savoreco.model.entity.UserAccount;
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
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "UserOrdersServlet",
        displayName = "UserOrders - Home",
        description = "UserOrders page",
        value = "/user/userOrders"
)
public class UserOrdersServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserOrdersServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/userOrders.jsp");

        UserAccount user = (UserAccount) request.getSession().getAttribute("user");

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query<BoughtFood> bFoodQuery = session.createQuery("FROM BoughtFood bf " +
                    "WHERE bf.purchase.user = :user ORDER BY bf.purchase.time LIMIT 20", BoughtFood.class);
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

            if((purchase != null)&&(mode.equals("confirmed"))&&(purchase.getStatus().equals(Purchase.Statuses.delivered))){
                purchase.setStatus(Purchase.Statuses.confirmed);
                session.merge(purchase);
                transaction.commit();
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                return;
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



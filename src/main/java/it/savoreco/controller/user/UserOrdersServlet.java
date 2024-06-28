package it.savoreco.controller.user;

import it.savoreco.model.entity.BoughtFood;
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
import java.util.List;

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
}



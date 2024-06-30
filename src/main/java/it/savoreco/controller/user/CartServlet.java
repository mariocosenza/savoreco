package it.savoreco.controller.user;

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
import java.util.Objects;

@WebServlet(
        name = "cartServlet",
        displayName = "Savoreco - Carrello",
        description = "Carrello home page",
        value = "/user/cart",
        asyncSupported = true
)
public class CartServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CartServlet.class);


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int foodId = 0;

        try {
            if (Objects.nonNull(req.getParameter("foodId"))) {
                foodId = Integer.parseInt(req.getParameter("foodId"));
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (NumberFormatException | IOException e) {
            logger.warn("Error sending error", e);
            return;
        }

        SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query<Basket> query = session.createQuery("from Basket where user = :user", Basket.class);
        query.setParameter("user", req.getSession().getAttribute("user"));
        var basket = query.getSingleResult();

        Query<BasketContain> queryBasketContain = session.createQuery("from BasketContain c where c.basket = basket and food.id = :foodId", BasketContain.class);
        queryBasketContain.setParameter("foodId", foodId);


        if (Objects.nonNull(req.getParameter("delete"))) {
            session.remove(queryBasketContain.list().getFirst());
            transaction.commit();
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else if (Objects.nonNull(req.getParameter("add"))) {
            queryBasketContain.setParameter("foodId", foodId);
            var basketContain = queryBasketContain.list();
            var food = session.get(Food.class, foodId);

            if (basketContain.isEmpty() && Objects.nonNull(food) && food.getAvailable()) {

                var foodInBasketID = new BasketContainId();
                foodInBasketID.setBasketId(basket.getId());
                foodInBasketID.setFoodId(foodId);

                var foodInBasket = new BasketContain();
                foodInBasket.setQuantity(1);
                foodInBasket.setFood(food);
                foodInBasket.setBasket(basket);
                foodInBasket.setId(foodInBasketID);

                session.persist(foodInBasket);
            } else if (!basketContain.isEmpty() && basketContain.getFirst().getFood().getAvailable()) {
                basketContain.getFirst().setQuantity(basketContain.getFirst().getQuantity() + 1);
                session.merge(basketContain.getFirst());
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            transaction.commit();
        }

    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/cart.jsp");
        try {
            var user = (UserAccount) req.getSession().getAttribute("user");
            Query<BasketContain> basketContainQuery = session.createQuery("from BasketContain b where b.basket.user = :user", BasketContain.class);
            basketContainQuery.setParameter("user", user);
            List<BasketContain> basketContains = basketContainQuery.list();
            transaction.commit();

            if (basketContains.isEmpty()) {
                req.setAttribute("noItem", 1);
            } else {
                req.setAttribute("noItem", 2);
                req.setAttribute("basketList", basketContains);
                req.setAttribute("tot", basketContains.stream().mapToDouble(b -> b.getFood().getPrice() * b.getQuantity()).sum());
            }

            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to cart.jsp", e);
        }
    }
}



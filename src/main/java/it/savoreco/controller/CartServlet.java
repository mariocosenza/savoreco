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
import java.util.Objects;

@WebServlet(
        name = "cartServlet",
        displayName = "Savoreco - Carrello",
        description = "Carrello home page",
        value = "/cart"
)
public class CartServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CartServlet.class);

    @SuppressWarnings("unchecked")
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        if (Objects.isNull(req.getParameter("add"))) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/cart.jsp");
            try {
                if(Objects.isNull(req.getSession(false)) || (Objects.nonNull(req.getSession(false)) && Objects.nonNull(req.getSession(false).getAttribute("cart")))) {
                    req.setAttribute("noItem", 1);
                }  else {

                    if(Objects.nonNull(req.getSession(false).getAttribute("user"))) {
                        var user = (UserAccount) req.getSession().getAttribute("user");
                        Query<BasketContain> basketContainQuery = session.createQuery("from BasketContain b where b.basket.user = :user", BasketContain.class);
                        basketContainQuery.setParameter("user", user);
                        List<BasketContain> basketContains = basketContainQuery.list();
                        transaction.commit();
                        if(basketContains.isEmpty()) {
                            System.out.println("test");
                            req.setAttribute("noItem", 1);
                        } else {
                            req.setAttribute("noItem", 2);
                            req.setAttribute("basketList", basketContains);
                            req.setAttribute("tot", basketContains.stream().mapToDouble(b -> b.getFood().getPrice()).sum());
                        }
                    }
                }
                requestDispatcher.forward(req, resp);
            } catch (IOException | ServletException e) {
                transaction.commit();
                logger.warn("Cannot forward to cart.jsp", e);
            }
        } /* else if (Objects.isNull(req.getSession(false)) || Objects.isNull(req.getSession(false).getAttribute("logged"))) {
            var httpSession = req.getSession();
            if (Objects.isNull(httpSession.getAttribute("cart"))) {
                var cartMap = new HashMap<Integer, Integer>();
                httpSession.setAttribute("cart", cartMap);
            }
            if (httpSession.getAttribute("cart") instanceof HashMap<?, ?> map) {
                var typeMap = (HashMap<Integer, Integer>) map;
                var quant = typeMap.get(Integer.parseInt(req.getParameter("foodId")));
                if (quant != null) {
                    quant++;
                    typeMap.replace(Integer.parseInt(req.getParameter("foodId")), quant);
                }
            }
        } else if (Objects.nonNull(req.getSession(false))
                && Objects.nonNull(req.getSession().getAttribute("logged"))
                && req.getSession().getAttribute("logged").equals("user")) {
            var user = (UserAccount) req.getSession().getAttribute("user");
            try {
                Transaction transaction = session.beginTransaction();
                Query<Basket> query = session.createQuery("FROM Basket b WHERE b.user = :user", Basket.class);
                var quantity = 1;
                var basket = query.stream().findAny().orElse(null);
                if (basket == null) {
                    basket = new Basket();
                    basket.setUser(user);
                    session.persist(basket);
                }
                var contains = new BasketContain();
                var id = new BasketContainId();
                Query<Food> foodQuery = session.createQuery("FROM Food WHERE id = :id", Food.class);
                foodQuery.setParameter("id", Integer.parseInt(req.getParameter("id")));
                var food = foodQuery.stream().findAny().orElseThrow();
                id.setBasketId(basket.getId());
                id.setFoodId(food.getId());
                contains.setId(id);
                contains.setBasket(basket);
                contains.setQuantity(quantity);
                contains.setFood(food);
                session.persist(contains);
                transaction.commit();
            } catch (Exception e) {
                logger.warn("Cannot persist cart.", e);
            }
        }*/
    }
}

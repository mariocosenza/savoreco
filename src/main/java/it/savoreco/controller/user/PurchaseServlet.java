package it.savoreco.controller.user;

import it.savoreco.model.entity.*;
import it.savoreco.service.PasswordSHA512;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(
        name = "purchaseServlet",
        displayName = "Pagamento",
        description = "Savoreco Pagamento",
        value = "/user/purchase"
)
public class PurchaseServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseServlet.class);
    private static final short IVA = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        var httpSession = req.getSession();
        httpSession.setAttribute("auth", null);
        httpSession.setAttribute("readyBoughtFood", null);
        httpSession.setAttribute("deliveryCost", null);
        try {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query<BasketContain> query = session.createQuery("from BasketContain where basket.user.id = :user_id", BasketContain.class);
            query.setParameter("user_id", ((UserAccount) req.getSession().getAttribute("user")).getId());
            var basketList = query.list();
            transaction.commit();
            if (basketList.isEmpty()) {
                resp.sendRedirect("/home");
                return;
            }

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/purchase.jsp");
            req.setAttribute("basketList", basketList);
            req.setAttribute("tot", basketList.stream().map(b -> b.getFood().getPrice() * b.getQuantity()).reduce(Double::sum).orElse(0.0) * ((IVA + 100) / 100.0));
            var delivery = basketList.stream().map(b -> b.getFood().getRestaurant()).distinct().map(Restaurant::getDeliveryCost).reduce(BigDecimal::add).orElse(new BigDecimal(0)).doubleValue();
            req.setAttribute("deliveryCost", delivery);
            var random = new SecureRandom();
            var number = String.valueOf(random.nextLong());
            req.setAttribute("auth", PasswordSHA512.SHA512Hash(number));
            httpSession.setAttribute("deliveryCost", delivery);
            httpSession.setAttribute("auth", number);
            httpSession.setAttribute("authTimer", Instant.now());
            httpSession.setAttribute("readyBoughtFood", basketList.stream().map(BasketContain::getFood).toList());
            httpSession.setAttribute("basketList", basketList);

            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to purchase.jsp", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {

        var httpSession = req.getSession();
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/purchaseStatus.jsp");

        if (Objects.isNull(req.getSession().getAttribute("auth"))
                || Objects.isNull(req.getParameter("auth"))
                || !req.getParameter("auth").equals(PasswordSHA512.SHA512Hash((String) httpSession.getAttribute("auth")))
                || Objects.isNull(httpSession.getAttribute("readyBoughtFood")) || Objects.isNull(req.getParameter("pick_up"))
                || Objects.isNull(httpSession.getAttribute("authTimer")) || Duration.between((Instant) httpSession.getAttribute("authTimer"), Instant.now()).toMinutes() > 10) {
            try {
                req.setAttribute("confirmed", false);
                requestDispatcher.include(req, resp);
            } catch (IOException | ServletException e) {
                logger.warn("Cannot forward to purchaseStatus.jsp", e);
            }
        } else {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            var user = session.get(UserAccount.class, ((UserAccount) req.getSession().getAttribute("user")).getId());

            var purchase = new Purchase();
            purchase.setIva(IVA);
            purchase.setUser(user);
            purchase.setTime(Instant.now());
            purchase.setPickUp(true);
            purchase.setPickUp(Boolean.parseBoolean(req.getParameter("pick_up")));
            if (!Boolean.parseBoolean(req.getParameter("pick_up"))) {
                purchase.setAddress(user.getAddress());
                purchase.setPickUp(false);
            }


            purchase.setPaymentMethod(Purchase.PaymentMethods.google);
            purchase.setTotalCost(BigDecimal.valueOf(((List<Food>) httpSession.getAttribute("readyBoughtFood")).stream().mapToDouble(Food::getPrice).sum()));
            purchase.setDeliveryCost(BigDecimal.valueOf((Double) httpSession.getAttribute("deliveryCost")));
            purchase.setStatus(Purchase.Statuses.payed);
            session.persist(purchase);

            
            
            var basketList = (List<BasketContain>) httpSession.getAttribute("basketList");
            for (var item : basketList) {
                var food = session.get(Food.class, item.getFood().getId());
                int quantity = item.getQuantity();
                if (food != null && food.getAvailable()) {
                    var boughtFood = new BoughtFood();
                        boughtFood.setPurchase(purchase);
                        boughtFood.setName(item.getFood().getName());
                        boughtFood.setGreenPoint(item.getFood().getGreenPoint() * quantity);
                        boughtFood.setQuantity((short) quantity);
                        boughtFood.setPrice(BigDecimal.valueOf(item.getFood().getPrice() * quantity));
                        boughtFood.setRestaurant(food.getRestaurant());
                        System.out.println(boughtFood);
                } else {
                   // session.refresh(food, LockMode.PESSIMISTIC_READ);
                    resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                user.setEcoPoint(user.getEcoPoint() + item.getFood().getGreenPoint() * quantity);
            }


            Query<BasketContain> query = session.createQuery("from BasketContain where basket.user.id = :user_id", BasketContain.class);
            query.setParameter("user_id", ((UserAccount) req.getSession().getAttribute("user")).getId());
            for (var basket : query.list()) {
                session.remove(basket);
            }

            httpSession.setAttribute("readyBoughtFood", null);
            transaction.commit();
            req.setAttribute("confirmed", true);
            try {
                requestDispatcher.include(req, resp);
            } catch (HibernateException | ServletException | IOException e) {
                logger.warn("Cannot forward to purchaseStatus.jsp", e);
                transaction.rollback();
            }
        }
    }
}

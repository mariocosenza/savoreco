package it.savoreco.controller;

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
import java.util.ArrayList;
import java.util.List;


@WebServlet(
        name = "searchServlet",
        displayName = "Risultati Ricerca",
        description = "Savoreco search page",
        value = "/search"
)
public class SearchServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SearchServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/search.jsp");
        try {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query<Integer> query = session.createNativeQuery("SELECT restaurant_id FROM savoreco.restaurant r " +
                    "INNER JOIN savoreco.address b " +
                    "ON r.street = b.street and r.zipcode = b.zipcode WHERE savoreco.st_distancesphere(savoreco.st_point(b.lon, b.lat, 4326), savoreco.st_point(:longitude, :latitude, 4326)) <= 30000", Integer.class);

            query.setParameter("longitude", Double.parseDouble(req.getParameter("lon")), Double.class);
            query.setParameter("latitude", Double.parseDouble(req.getParameter("lat")), Double.class);
            List<Restaurant> restaurantList = new ArrayList<>();
            for (Integer restaurantId : query.list()) {
                Query<Restaurant> restaurantQuery = session.createQuery("FROM Restaurant r WHERE r.id = :id", Restaurant.class);
                restaurantQuery.setParameter("id", restaurantId);
                restaurantList.add(restaurantQuery.getSingleResult());
            }

            transaction.commit();
            req.setAttribute("restaurants", restaurantList);
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to search.jsp", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }
}

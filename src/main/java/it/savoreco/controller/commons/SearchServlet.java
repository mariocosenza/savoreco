package it.savoreco.controller.commons;

import it.savoreco.model.entity.Restaurant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.lucene.util.SloppyMath;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;


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

        if (Objects.isNull(req.getParameter("lat")) || Objects.isNull(req.getParameter("lon"))) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/search.jsp");
        try {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            var lon = Double.parseDouble(req.getParameter("lon"));
            var lat = Double.parseDouble(req.getParameter("lat"));

            Query<Restaurant> query;
            if (Objects.nonNull(req.getParameter("byName")) && !req.getParameter("byName").isEmpty()) {
                query = session.createNativeQuery("SELECT r.restaurant_id, r.name, r.street, r.zipcode, r.description, r.image_object, r.delivery_cost, r.category, r.deleted, r.creation_time  FROM savoreco.restaurant r " +
                        "INNER JOIN savoreco.address b " +
                        "ON r.street = b.street and r.zipcode = b.zipcode WHERE savoreco.st_distancesphere(savoreco.st_point(b.lon, b.lat, 4326), savoreco.st_point(:longitude, :latitude, 4326)) <= 10000 and r.deleted is false and savoreco.similarity(r.name, :name) > 0.3 LIMIT :maxResult", Restaurant.class);
                query.setParameter("name", req.getParameter("byName"));
            } else if (Objects.isNull(req.getParameter("sort")) || req.getParameter("sort").equals("distance")) {
                query = session.createNativeQuery("SELECT r.restaurant_id, r.name, r.street, r.zipcode, r.description, r.image_object, r.delivery_cost, r.category, r.deleted, r.creation_time FROM savoreco.restaurant r " +
                        "INNER JOIN savoreco.address b " +
                        "ON r.street = b.street and r.zipcode = b.zipcode WHERE savoreco.st_distancesphere(savoreco.st_point(b.lon, b.lat, 4326), savoreco.st_point(:longitude, :latitude, 4326)) <= 10000 and r.deleted is false LIMIT :maxResult", Restaurant.class);

            } else if (req.getParameter("sort").equals("name")) {
                query = session.createNativeQuery("SELECT r.restaurant_id, r.name, r.street, r.zipcode, r.description, r.image_object, r.delivery_cost, r.category, r.deleted, r.creation_time  FROM savoreco.restaurant r " +
                        "INNER JOIN savoreco.address b " +
                        "ON r.street = b.street and r.zipcode = b.zipcode WHERE savoreco.st_distancesphere(savoreco.st_point(b.lon, b.lat, 4326), savoreco.st_point(:longitude, :latitude, 4326)) <= 10000 and r.deleted is false ORDER BY UPPER(r.name) LIMIT  :maxResult", Restaurant.class);
            } else {
                query = session.createNativeQuery("SELECT r.restaurant_id, r.name, r.street, r.zipcode, r.description, r.image_object, r.delivery_cost, r.category, r.deleted, r.creation_time FROM savoreco.restaurant r " +
                        "INNER JOIN savoreco.address b " +
                        "ON r.street = b.street and r.zipcode = b.zipcode WHERE savoreco.st_distancesphere(savoreco.st_point(b.lon, b.lat, 4326), savoreco.st_point(:longitude, :latitude, 4326)) <= 10000 and r.deleted is false ORDER BY r.delivery_cost LIMIT :maxResult", Restaurant.class);
            }


            int result = 10;
            if (Objects.nonNull(req.getParameter("maxResult"))) {
                result = Math.max(Integer.parseInt(req.getParameter("maxResult")), 10);
            }

            query.setParameter("maxResult", result);
            query.setParameter("longitude", lon);
            query.setParameter("latitude", lat);
            var restaurantList = query.list();

            if (Objects.isNull(req.getParameter("sort")) || req.getParameter("sort").equals("distance")) {
                restaurantList.sort(Comparator.comparingDouble(a -> SloppyMath.haversinMeters(a.getAddress().getLat(), a.getAddress().getLon(), lat, lon)));
            }

            transaction.commit();
            if (restaurantList.size() < result) {
                req.setAttribute("maxResult", 0);
            } else {
                req.setAttribute("maxResult", result + 5);
            }
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

package it.savoreco.controller;

import com.google.common.html.HtmlEscapers;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import it.savoreco.model.entity.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.dialect.function.array.PostgreSQLArrayFillFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(
        name = "AddRestaurant Page",
        value = "/seller/addRestaurant",
        asyncSupported = true
)
public class AddRestaurantServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddRestaurantServlet.class);

    private Pattern nameMatcher;
    private Pattern descriptionMatcher;
    private Pattern deliveryCostMatcher;
    private Pattern categoryMatcher;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        nameMatcher = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\s]{2,24}$");
        descriptionMatcher = Pattern.compile("^.{2,2000}$");
        deliveryCostMatcher = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
        categoryMatcher = Pattern.compile("^[a-zA-Z\\s]{2,25}$");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map;

        try {
            Gson gson = new Gson();
            map = gson.fromJson(req.getReader(), mapType);
        } catch (IOException e) {
            logger.error("Error parsing JSON", e);
            return;
        }

        var name = map.get("name").trim();
        var street = map.get("address").trim();
        var zipcode = map.get("postal").trim();
        var city = map.get("city").trim();
        var lat = map.get("lat");
        var lon = map.get("lon");
        var description = map.get("description");
        var deliveryCost = map.get("deliveryCost");
        var category = map.get("category").trim();
        var imageUrl = map.get("imageUrl").trim();

        SellerAccount seller = (SellerAccount) req.getSession().getAttribute("seller");

        if (nameMatcher.matcher(name).matches()
                && descriptionMatcher.matcher(description).matches()
                && deliveryCostMatcher.matcher(deliveryCost).matches()
                && categoryMatcher.matcher(category).matches()) {

            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            try {
                var addressId = new AddressId();
                addressId.setStreet(HtmlEscapers.htmlEscaper().escape(street));
                addressId.setZipcode(HtmlEscapers.htmlEscaper().escape(zipcode.trim()));

                var address = session.get(Address.class, addressId);

                if(address == null) {
                    address = new Address();
                    address.setId(addressId);
                    address.setCity(HtmlEscapers.htmlEscaper().escape(city));
                    address.setCountryCode("IT");
                    address.setLat(Double.valueOf(lat));
                    address.setLon(Double.valueOf(lon));
                    session.persist(address);
                }

                var restaurant = new Restaurant();
                restaurant.setName(HtmlEscapers.htmlEscaper().escape(name));
                restaurant.setAddress(address);
                restaurant.setDescription(HtmlEscapers.htmlEscaper().escape(description));
                restaurant.setDeliveryCost(BigDecimal.valueOf(Double.parseDouble(deliveryCost.trim())));
                restaurant.setCategory(HtmlEscapers.htmlEscaper().escape(category.trim()));
                restaurant.setCreationTime(Instant.now());
                restaurant.setImageObject(HtmlEscapers.htmlEscaper().escape(imageUrl));
                restaurant.setDeleted(false);

                seller.setRestaurant(restaurant);


                session.persist(restaurant);
                session.merge(seller);
                transaction.commit();
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Error saving restaurant", e);
                try {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } catch (IOException ioException) {
                    logger.warn("Error sending error", ioException);
                }
            }
        } else {
            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException e) {
                logger.warn("Error sending error", e);
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/seller/addRestaurant.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to addRestaurant.jsp", e);
        }
    }
}
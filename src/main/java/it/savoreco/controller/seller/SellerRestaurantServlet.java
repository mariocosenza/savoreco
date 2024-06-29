package it.savoreco.controller.seller;

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
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(
        name = "sellerRestaurantServlet",
        displayName = "SellerRestaurant - Home",
        description = "SellerRestaurant management page",
        value = "/seller/sellerRestaurant"
)
public class SellerRestaurantServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SellerRestaurantServlet.class);

    private Pattern nameMatcher;
    private Pattern descriptionMatcher;
    private Pattern categoryMatcher;
    private Pattern priceMatcher;
    private Pattern allergensMatcher;
    private Pattern greenPointsMatcher;
    private Pattern quantityMatcher;
    private Pattern idMatcher;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        nameMatcher = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\s]{2,24}$");
        descriptionMatcher = Pattern.compile("^.{2,2000}$");
        categoryMatcher = Pattern.compile("^[a-zA-Z\\s]{2,25}$");
        priceMatcher = Pattern.compile("^\\d+(\\.\\d{1,2})?$|^\\d+(,\\d{1,2})?$");
        allergensMatcher = Pattern.compile("^[A-Za-z]+(?:,\\s*[A-Za-z]+){0,49}$");
        greenPointsMatcher = Pattern.compile("^\\d{1,2}$");
        quantityMatcher = Pattern.compile("^\\d{1,5}$");
        idMatcher = Pattern.compile("^\\d+$");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/seller/sellerRestaurant.jsp");

        SellerAccount seller = (SellerAccount) req.getSession().getAttribute("seller");

        try {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Restaurant restaurant = session.get(Restaurant.class, seller.getRestaurant().getId());

            Query<Food> foodQuery = session.createQuery("FROM Food f " +
                    "WHERE f.restaurant = :restaurant", Food.class);
            foodQuery.setParameter("restaurant", restaurant);
            List<Food> products = foodQuery.list();

            Query<BigDecimal> costQuery = session.createQuery("SELECT SUM(bf.price * bf.quantity) FROM BoughtFood bf " +
                    "WHERE bf.restaurant = :restaurant", BigDecimal.class);
            costQuery.setParameter("restaurant", restaurant);
            String totalCost = (costQuery.getSingleResult() == null) ? "0.00" : String.format("%.2f", costQuery.getSingleResult().doubleValue());

            transaction.commit();

            Food emptyFood = new Food();
            emptyFood.setName("");
            emptyFood.setDescription("");
            emptyFood.setCategory("");
            emptyFood.setPrice(0.00);
            emptyFood.setAllergens("");
            emptyFood.setGreenPoint(0);
            emptyFood.setQuantity((short) 0);
            emptyFood.setId(null);
            emptyFood.setRestaurant(restaurant);
            products.addLast(emptyFood);

            req.setAttribute("products", products);
            req.setAttribute("restaurant", restaurant);
            req.setAttribute("totalCost", totalCost);


            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to sellerRestaurant.jsp", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map;

        try {
            Gson gson = new Gson();
            map = gson.fromJson(req.getReader(), mapType);
        } catch (Exception e) {
            logger.error("Error parsing JSON", e);
            return;
        }

        SellerAccount seller = (SellerAccount) req.getSession().getAttribute("seller");

        if (map.get("mode").equals("saveFood")) {
            var name = map.get("fname").trim();
            var description = map.get("fdescription").trim();
            var category = map.get("fcategory").trim();
            var price = map.get("price").trim();
            var allergens = map.get("allergens").trim();
            var greenPoints = map.get("greenPoints").trim();
            var imageUrl = map.get("imageUrl").trim();
            var quantity = map.get("quantity").trim();
            var id = map.get("id").trim();

            if (nameMatcher.matcher(name).matches()
                    && descriptionMatcher.matcher(description).matches()
                    && categoryMatcher.matcher(category).matches()
                    && priceMatcher.matcher(price).matches()
                    && allergensMatcher.matcher(allergens).matches()
                    && greenPointsMatcher.matcher(greenPoints).matches()
                    && quantityMatcher.matcher(greenPoints).matches()) {

                SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
                Session session = sessionFactory.getCurrentSession();
                Transaction transaction = session.beginTransaction();

                try {
                    Restaurant restaurant = session.get(Restaurant.class, seller.getRestaurant().getId());

                    Food food = new Food();
                    food.setName(HtmlEscapers.htmlEscaper().escape(name));
                    food.setDescription(HtmlEscapers.htmlEscaper().escape(description));
                    food.setCategory(HtmlEscapers.htmlEscaper().escape(category));
                    food.setPrice(Double.parseDouble(price.replace(',','.')));
                    food.setAllergens(HtmlEscapers.htmlEscaper().escape(allergens));
                    food.setGreenPoint(Integer.parseInt(greenPoints));
                    food.setImageObject(HtmlEscapers.htmlEscaper().escape(imageUrl));
                    short quan = (short) Integer.parseInt(quantity);
                    food.setQuantity(quan);
                    food.setAvailable(quan > 0);
                    food.setRestaurant(restaurant);

                    if (id.equals("null")) {
                        session.persist(food);
                    } else {
                        food.setId(Integer.parseInt(id));
                        session.merge(food);
                    }

                    transaction.commit();
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);

                } catch (Exception e) {
                    transaction.rollback();
                    logger.error("Error saving food", e);
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
        } else if (map.get("mode").equals("modifyRestaurant")) {
            var name = map.get("name").trim();
            var description = map.get("description").trim();
            var category = map.get("category").trim();
            var deliveryCost = map.get("deliveryCost").trim();
            var street = map.get("address").trim();
            var zipcode = map.get("postal").trim();
            var city = map.get("city").trim();
            var lat = map.get("lat");
            var lon = map.get("lon");
            var logoUrl = map.get("logoUrl").trim();

            if (nameMatcher.matcher(name).matches()
                    && descriptionMatcher.matcher(description).matches()
                    && categoryMatcher.matcher(category).matches()
                    && priceMatcher.matcher(deliveryCost).matches()) {

                SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
                Session session = sessionFactory.getCurrentSession();
                Transaction transaction = session.beginTransaction();

                try {
                    Restaurant restaurant = session.get(Restaurant.class, seller.getRestaurant().getId());

                    Address address;

                    if (!street.isEmpty()) {
                        var addressId = new AddressId();
                        addressId.setStreet(HtmlEscapers.htmlEscaper().escape(street));
                        addressId.setZipcode(HtmlEscapers.htmlEscaper().escape(zipcode));

                        address = session.get(Address.class, addressId);

                        if (address == null) {
                            address = new Address();
                            address.setId(addressId);
                            address.setCity(HtmlEscapers.htmlEscaper().escape(city));
                            address.setCountryCode("IT");
                            address.setLat(Double.valueOf(lat));
                            address.setLon(Double.valueOf(lon));
                            session.persist(address);
                        }
                    } else {
                        address = restaurant.getAddress();
                    }

                    restaurant.setName(HtmlEscapers.htmlEscaper().escape(name));
                    restaurant.setAddress(address);
                    restaurant.setDescription(HtmlEscapers.htmlEscaper().escape(description));
                    restaurant.setDeliveryCost(BigDecimal.valueOf(Double.parseDouble(deliveryCost.replace(',','.'))));
                    restaurant.setCategory(HtmlEscapers.htmlEscaper().escape(category));
                    restaurant.setCreationTime(Instant.now());
                    restaurant.setImageObject(HtmlEscapers.htmlEscaper().escape(logoUrl));
                    restaurant.setDeleted(false);

                    session.merge(restaurant);
                    transaction.commit();
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);

                } catch (Exception e) {
                    transaction.rollback();
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
        } else if (map.get("mode").equals("deleteFood")) {
            var id = map.get("id").trim();

            if (idMatcher.matcher(id).matches()) {
                SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
                Session session = sessionFactory.getCurrentSession();
                Transaction transaction = session.beginTransaction();

                try {
                    Restaurant restaurant = session.get(Restaurant.class, seller.getRestaurant().getId());

                    Query<Food> foodQuery = session.createQuery("FROM Food f " +
                            "WHERE f.id = :id AND f.restaurant = :restaurant", Food.class);
                    foodQuery.setParameter("id", id);
                    foodQuery.setParameter("restaurant", restaurant);
                    Food food = foodQuery.getSingleResult();

                    if(food != null){
                        session.remove(food);
                    }

                    transaction.commit();
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);

                } catch (Exception e) {
                    transaction.rollback();
                    logger.error("Error deleting food", e);
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
        } else {
            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException e) {
                logger.warn("Error sending error", e);
            }
        }
    }
}

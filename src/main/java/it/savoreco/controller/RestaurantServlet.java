package it.savoreco.controller;

import it.savoreco.model.entity.Address;
import it.savoreco.model.entity.AddressId;
import it.savoreco.model.entity.Food;
import it.savoreco.model.entity.Restaurant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "restaurantServlet",
        displayName = "Restaurant - Home",
        description = "Restaurant page",
        value = "/restaurant"
)
public class RestaurantServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/restaurant.jsp");

        List<Food> foodList = new ArrayList<>();

        Food food1 = new Food();
        food1.setName("Pizza Margherita");
        food1.setQuantity((short) 10);
        food1.setDescription("Pizza classica");
        food1.setCategory("Pizza");
        food1.setImageObject("../assets/images/savoreco-logo.webp");
        food1.setGreenPoint(5);
        food1.setAllergens("Glutine");

        Food food2 = new Food();
        food2.setName("Pizza Ortolana");
        food2.setQuantity((short) 5);
        food2.setDescription("Pizza vegetariana con verdure fresche.");
        food2.setCategory("Pizza");
        food2.setImageObject("../assets/images/savoreco-logo.webp");
        food2.setGreenPoint(8);
        food2.setAllergens("Glutine, Lattosio");

        Food food3 = new Food();
        food3.setName("Hamburger");
        food3.setQuantity((short) 15);
        food3.setDescription("Hamburger di manzo con patatine.");
        food3.setCategory("Panini");
        food3.setImageObject("../assets/images/savoreco-logo.webp");
        food3.setGreenPoint(3);
        food3.setAllergens("Glutine, Uova");

        Food food4 = new Food();
        food4.setName("Hotdog");
        food4.setQuantity((short) 20);
        food4.setDescription("Hotdog con ketchup e maionese.");
        food4.setCategory("Panini");
        food4.setImageObject("../assets/images/savoreco-logo.webp");
        food4.setGreenPoint(3);
        food4.setAllergens("Glutine, Uova");

        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);

        AddressId id = new AddressId();
        id.setStreet("Via roma");
        id.setZipcode("80000");

        Address address = new Address();
        address.setCity("Potenza");
        address.setCountryCode("IT");
        address.setId(id);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Goloserie");
        restaurant.setAddress(address);
        restaurant.setDescription("Un fantastico ristorante italiano.");
        restaurant.setImageObject("../assets/images/restaurant-logo.webp");
        restaurant.setDeliveryCost(BigDecimal.valueOf(5.0));
        restaurant.setCategory("Italiano");
        restaurant.setImageObject("../assets/images/savoreco-logo.webp");

        request.setAttribute("foodList", foodList);
        request.setAttribute("restaurant", restaurant);

        try {
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to index.jsp", e);
        }
    }
}

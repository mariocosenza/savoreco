package it.savoreco.controller;

import it.savoreco.model.entity.*;
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
        name = "ordersServlet",
        displayName = "Orders - Home",
        description = "Orders page",
        value = "/orders"
)
public class OrdersServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrdersServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/seller/orders.jsp");

        AddressId id = new AddressId();
        id.setStreet("Via roma");
        id.setZipcode("80000");

        Address address = new Address();
        address.setCity("Potenza");
        address.setCountryCode("IT");
        address.setId(id);

        UserAccount user =  new UserAccount();
        user.setName("mario");
        user.setSurname("rossi");
        user.setEmail("mariorossi@gmail.com");
        //user.setAge();
        user.setDeleted(false);
        user.setCountryCode("IT");
        user.setAddress(address);


        Purchase purchase = new Purchase();
        //purchase.setStatus();
        purchase.setIva((short) 2);
        //purchase.setTime();
        purchase.setDeliveryCost(BigDecimal.valueOf(2.0));
        //purchase.setPaymentMethod();
        purchase.setTotalCost(BigDecimal.valueOf(4));
        purchase.setUser(user);

        Food food = new Food();
        food.setName("Hotdog");
        food.setQuantity((short) 20);
        food.setDescription("Hotdog con ketchup e maionese.");
        food.setCategory("Panini");
        food.setImageObject("../assets/images/savoreco-logo.webp");
        food.setGreenPoint(3);
        food.setAllergens("Glutine, Uova");


        BoughtFood boughtFood = new BoughtFood();
        boughtFood.setFood(food);
        boughtFood.setPurchase(purchase);
        boughtFood.setName(food.getName());
        boughtFood.setQuantity((short) 1);
        //boughtFood.setTime();
        boughtFood.setGreenPoint(food.getGreenPoint());
        boughtFood.setPrice(BigDecimal.valueOf(2));


        Food food_2 = new Food();
        food_2.setName("Hamburger");
        food_2.setQuantity((short) 20);
        food_2.setDescription("Hamburger con ketchup e maionese.");
        food_2.setCategory("Panini");
        food_2.setImageObject("../assets/images/savoreco-logo.webp");
        food_2.setGreenPoint(3);
        food_2.setAllergens("Glutine, Uova");


        BoughtFood boughtFood_2 = new BoughtFood();
        boughtFood_2.setFood(food_2);
        boughtFood_2.setPurchase(purchase);
        boughtFood_2.setName(food_2.getName());
        boughtFood_2.setQuantity((short) 1);
        //boughtFood_2.setTime();
        boughtFood_2.setGreenPoint(food_2.getGreenPoint());
        boughtFood_2.setPrice(BigDecimal.valueOf(2));



        AddressId id2 = new AddressId();
        id2.setStreet("Via milano");
        id2.setZipcode("80000");

        Address address2 = new Address();
        address2.setCity("Potenza");
        address2.setCountryCode("IT");
        address2.setId(id2);

        UserAccount user2 =  new UserAccount();
        user2.setName("sara");
        user2.setSurname("bianca");
        user2.setEmail("srabianca@gmail.com");
        //user2.setAge();
        user2.setDeleted(false);
        user2.setCountryCode("IT");
        user2.setAddress(address2);


        Purchase purchase2 = new Purchase();
        //purchase2.setStatus();
        purchase2.setIva((short) 2);
        //purchase2.setTime();
        purchase2.setDeliveryCost(BigDecimal.valueOf(2.0));
        //purchase2.setPaymentMethod();
        purchase2.setTotalCost(BigDecimal.valueOf(4));
        purchase2.setUser(user2);

        Food food2 = new Food();
        food2.setName("Torta");
        food2.setQuantity((short) 20);
        food2.setDescription("Hotdog con ketchup e maionese.");
        food2.setCategory("Dolce");
        food2.setImageObject("../assets/images/savoreco-logo.webp");
        food2.setGreenPoint(3);
        food2.setAllergens("Glutine, Uova");


        BoughtFood boughtFood2 = new BoughtFood();
        boughtFood2.setFood(food2);
        boughtFood2.setPurchase(purchase2);
        boughtFood2.setName(food2.getName());
        boughtFood2.setQuantity((short) 1);
        //boughtFood2.setTime();
        boughtFood2.setGreenPoint(food2.getGreenPoint());
        boughtFood2.setPrice(BigDecimal.valueOf(2));


        Food food2_2 = new Food();
        food2_2.setId(1);
        food2_2.setName("Fragole");
        food2_2.setQuantity((short) 20);
        food2_2.setDescription("Hamburger con ketchup e maionese.");
        food2_2.setCategory("Frutta");
        food2_2.setImageObject("../assets/images/savoreco-logo.webp");
        food2_2.setGreenPoint(3);
        food2_2.setAllergens("Glutine, Uova");


        BoughtFood boughtFood2_2 = new BoughtFood();
        boughtFood2_2.setFood(food2_2);
        boughtFood2_2.setPurchase(purchase2);
        boughtFood2_2.setName(food2_2.getName());
        boughtFood2_2.setQuantity((short) 1);
        //boughtFood2_2.setTime();
        boughtFood2_2.setGreenPoint(food2_2.getGreenPoint());
        boughtFood2_2.setPrice(BigDecimal.valueOf(2));

        List<BoughtFood> orders = new ArrayList<>();

        orders.add(boughtFood);
        orders.add(boughtFood_2);
        orders.add(boughtFood2);
        orders.add(boughtFood2_2);

        request.setAttribute("orders", orders);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Goloserie");
        restaurant.setAddress(address);
        restaurant.setDescription("Un fantastico ristorante italiano.");
        restaurant.setImageObject("../assets/images/restaurant-logo.webp");
        restaurant.setDeliveryCost(BigDecimal.valueOf(5.0));
        restaurant.setCategory("Italiano");
        restaurant.setImageObject("../assets/images/savoreco-logo.webp");

        request.setAttribute("restaurant", restaurant);


        try {
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to index.jsp", e);
        }
    }
}



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
import java.util.HashMap;
import java.util.List;

@WebServlet(
        name = "moderatorPageServlet",
        displayName = "Moderator - Home",
        description = "Moderator page",
        value = "/moderatorPage"
)
public class ModeratorPageServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ModeratorPageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/moderator/moderatorPage.jsp");

        List<UserAccount> usersList = new ArrayList<>();

        UserAccount user = new UserAccount();
        user.setName("mario");
        user.setSurname("rossi");
        user.setDeleted(false);
        //user.setAge();
        //user.setExpires();

        UserAccount user2 = new UserAccount();
        user2.setName("luigi");
        user2.setSurname("verde");
        user2.setDeleted(false);
        //user.setAge();
        //user.setExpires();

        UserAccount user3 = new UserAccount();
        user3.setName("prim");
        user3.setSurname("pich");
        user3.setDeleted(true);
        //user.setAge();
        //user.setExpires();

        UserAccount user4 = new UserAccount();
        user4.setName("bowser");
        user4.setSurname("koop");
        user4.setDeleted(true);
        //user.setAge();
        //user.setExpires();

        UserAccount user5 = new UserAccount();
        user5.setName("Toad");
        user5.setSurname("Mushroom");
        user5.setDeleted(false);
        //user.setAge();
        //user.setExpires();

        UserAccount user6 = new UserAccount();
        user6.setName("Peach");
        user6.setSurname("Toadstool");
        user6.setDeleted(false);
        //user.setAge();
        //user.setExpires();

        UserAccount user7 = new UserAccount();
        user7.setName("Yoshi");
        user7.setSurname("Dragon");
        user7.setDeleted(true);
        //user.setAge();
        //user.setExpires();

        UserAccount user8 = new UserAccount();
        user8.setName("Daisy");
        user8.setSurname("Flower");
        user8.setDeleted(false);
        //user.setAge();
        //user.setExpires();


        usersList.add(user);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);
        usersList.add(user5);
        usersList.add(user6);
        usersList.add(user7);
        usersList.add(user8);

        request.setAttribute("usersList", usersList);

        HashMap<UserAccount, Integer> greenPointMap = new HashMap<>();
        greenPointMap.put(user, 20);
        greenPointMap.put(user2, 30);
        greenPointMap.put(user3, 45);
        greenPointMap.put(user4, 5);
        greenPointMap.put(user5, 15);
        greenPointMap.put(user6, 25);
        greenPointMap.put(user7, 50);
        greenPointMap.put(user8, 35);

        request.setAttribute("greenPointMap", greenPointMap);


        try {
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to index.jsp", e);
        }
    }
}



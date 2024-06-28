package it.savoreco.controller.commons;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import it.savoreco.model.entity.SellerAccount;
import it.savoreco.model.entity.UserAccount;
import it.savoreco.service.PasswordSHA512;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;


@WebServlet(
        name = "registrationPage",
        value = "/registration",
        asyncSupported = true
)
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

    private Pattern usernameMatcher;
    private Pattern passwordMatcher;
    private Pattern emailMatcher;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        usernameMatcher = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_]{2,15}$");
        passwordMatcher = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        emailMatcher = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
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

        if (Objects.isNull(map.get("profile_type"))
                || (!map.get("profile_type").equals("user")
                && !map.get("profile_type").equals("seller"))) {

            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException e) {
                logger.warn("Error sending error", e);
            }
        }

        var name = map.get("name");
        var surname = map.get("surname");
        var password = map.get("password");
        var email = map.get("email");

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.ITALY);


        if (Objects.nonNull(name) && Objects.nonNull(surname)
                && usernameMatcher.matcher(name).matches()
                && usernameMatcher.matcher(surname).matches()
                && emailMatcher.matcher(email).matches()
                && passwordMatcher.matcher(password).matches()) {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            switch (map.get("profile_type")) {
                case "user" -> {
                    var user = new UserAccount();
                    user.setName(name.trim());
                    user.setSurname(surname.trim());
                    user.setEmail(email.trim());
                    user.setBirthdate(LocalDate.parse(Objects.requireNonNullElse(map.get("age"), "1900-01-01"), formatter));
                    user.setPassword(PasswordSHA512.SHA512Hash(password));
                    user.setCountryCode("IT");
                    user.setEcoPoint(0);
                    session.persist(user);
                    transaction.commit();
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                }
                case "seller" -> {
                    var seller = new SellerAccount();
                    seller.setName(name.trim());
                    seller.setSurname(surname.trim());
                    seller.setEmail(email.trim());
                    seller.setPassword(PasswordSHA512.SHA512Hash(password));
                    session.persist(seller);
                    transaction.commit();
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            if (Objects.isNull(req.getSession(false)) || Objects.isNull(req.getSession(false).getAttribute("logged"))) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/registration.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("/home");
            }
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to registration.jsp", e);
        }
    }
}

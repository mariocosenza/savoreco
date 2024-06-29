package it.savoreco.controller.user;

import com.google.common.html.HtmlEscapers;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import it.savoreco.model.entity.Address;
import it.savoreco.model.entity.AddressId;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@WebServlet(
        name = "preferenceServlet",
        displayName = "Savoreco - Preferenze",
        description = "Preferenze user",
        value = "/user/preference"
)
public class UserPreferenceServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserPreferenceServlet.class);
    private Pattern passwordMatcher;
    private Pattern emailMatcher;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        passwordMatcher = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        emailMatcher = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/preference.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to preference.jsp", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        if (Objects.nonNull(req.getParameter("delete"))) {
            var user = (UserAccount) req.getSession().getAttribute("user");
            user.setDeleted(true);
            user.setExpires(Instant.now().plus(7, ChronoUnit.DAYS));
            session.merge(user);
            transaction.commit();
            req.getSession().invalidate();
            try {
                resp.sendRedirect("/home");
            } catch (IOException e) {
                logger.warn("Cannot redirect to home", e);
                transaction.rollback();
                resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }
        }

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


        try {
            var check = false;
            var userAccount = (UserAccount) req.getSession().getAttribute("user");

            if (emailMatcher.matcher(map.get("email")).matches() && passwordMatcher.matcher(map.get("password")).matches() && Objects.nonNull(map.get("old_password"))) {

                var oldPassword = PasswordSHA512.SHA512Hash(map.get("old_password"));
                var newPassword = PasswordSHA512.SHA512Hash(map.get("password"));
                var user = session.get(UserAccount.class, userAccount.getId());
                if (user.getPassword().equals(oldPassword) && !newPassword.equals(oldPassword)) {
                    userAccount.setEmail(map.get("email").trim());
                    userAccount.setPassword(newPassword);
                    check = true;
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    return;
                }
            }


            if (Objects.nonNull(map.get("lat"))
                    && Objects.nonNull(map.get("lon"))
                    && Objects.nonNull(map.get("address"))
                    && Objects.nonNull(map.get("postal"))
                    && Objects.nonNull(map.get("city"))) {
                var lat = Double.valueOf(map.get("lat"));
                var lon = Double.valueOf(map.get("lon"));
                var street = HtmlEscapers.htmlEscaper().escape(map.get("address")).trim();
                var zipcode = HtmlEscapers.htmlEscaper().escape(map.get("postal")).trim();
                var city = HtmlEscapers.htmlEscaper().escape(map.get("city")).trim();
                var address = new Address();
                var id = new AddressId();
                address.setCountryCode("IT");
                address.setLat(lat);
                address.setLon(lon);
                id.setStreet(street);
                id.setZipcode(zipcode);
                address.setCity(city);
                address.setId(id);
                if (session.get(Address.class, id) == null) {
                    session.persist(address);
                }
                userAccount.setAddress(address);
            }
            session.merge(userAccount);
            transaction.commit();

            if (check) {
                req.getSession().invalidate();
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            transaction.rollback();
            logger.warn("Cannot get UserAccount", e);
        }
    }
}

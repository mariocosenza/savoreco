package it.savoreco.controller;

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
import java.util.Map;
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
        System.out.println(req.getSession().getAttribute("user"));
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/preference.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to preference.jsp", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map = null;
        try {
            Gson gson = new Gson();
            map = gson.fromJson(req.getReader(), mapType);
        } catch (IOException e) {
            logger.error("Error parsing JSON", e);
            return;
        }

        SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            var userAccount = (UserAccount) req.getSession().getAttribute("user");
            if (emailMatcher.matcher(map.get("email")).matches() && passwordMatcher.matcher(map.get("password")).matches()) {
                userAccount.setEmail(map.get("email"));
                userAccount.setPassword(PasswordSHA512.SHA512Hash(map.get("password")));
            }
                var address = new Address();
                address.setCountryCode("IT");
                address.setLat(Double.valueOf(map.get("lat")));
                address.setLon(Double.valueOf(map.get("lon")));
                var id = new AddressId();
                id.setStreet(HtmlEscapers.htmlEscaper().escape(map.get("address")));
                id.setZipcode(HtmlEscapers.htmlEscaper().escape(map.get("postal")));
                address.setCity(HtmlEscapers.htmlEscaper().escape(map.get("city")));
                address.setId(id);
                if(session.get(Address.class, id) == null) {
                    session.persist(address);
                }
                userAccount.setAddress(address);
                session.merge(userAccount);
                transaction.commit();
                resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.warn("Cannot get UserAccount", e);
        }
    }
}

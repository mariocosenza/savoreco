package it.savoreco.controller.moderator;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import it.savoreco.model.entity.UserAccount;
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
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "moderatorPageServlet",
        displayName = "Moderator - Home",
        description = "Moderator page",
        value = "/moderator/moderatorPage"
)
public class ModeratorPageServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ModeratorPageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/moderator/moderatorPage.jsp");

        try {
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query<UserAccount> userQuery = session.createQuery("FROM UserAccount us ", UserAccount.class);
            List<UserAccount> usersList = userQuery.list();

            transaction.commit();

            request.setAttribute("usersList", usersList);

            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to moderatorPage.jsp", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map;

        try {
            Gson gson = new Gson();
            map = gson.fromJson(request.getReader(), mapType);
        } catch (Exception e) {
            logger.error("Error parsing JSON", e);
            return;
        }

        var id = map.get("id").trim();
        var mode = map.get("mode").trim();

        SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query<UserAccount> userQuery = session.createQuery("FROM UserAccount u " +
                    "WHERE u.id = :id", UserAccount.class);
            userQuery.setParameter("id", id);
            UserAccount user = userQuery.getSingleResult();

            if (user != null) {
                if (mode.equals("delete")) {
                    user.setDeleted(true);
                    user.setExpires(Instant.now().plusSeconds(30 * 24 * 60 * ((long) 60)));//30 giorni

                    session.merge(user);
                    transaction.commit();
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    return;

                } else if (mode.equals("recover")) {
                    user.setDeleted(false);
                    user.setExpires(null);

                    session.merge(user);
                    transaction.commit();
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);

                    return;
                }
            }

            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException e) {
                logger.warn("Error sending error", e);
            }


        } catch (Exception e) {
            transaction.rollback();
            logger.error("Error deleting or recovering user", e);
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ioException) {
                logger.warn("Error sending error", ioException);
            }
        }
    }
}



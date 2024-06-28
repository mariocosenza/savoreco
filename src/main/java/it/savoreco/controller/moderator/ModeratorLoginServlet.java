package it.savoreco.controller.moderator;

import it.savoreco.model.entity.ModeratorAccount;
import it.savoreco.service.PasswordSHA512;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "moderatorLogin",
        displayName = "Login Moderatore",
        description = "Moderator loginPage",
        value = "/login/moderator"
)
public class ModeratorLoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ModeratorLoginServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if ((Objects.nonNull(req.getSession(false)) && Objects.nonNull(req.getSession(false).getAttribute("logged")))) {
                resp.sendRedirect("/home");
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
                req.setAttribute("moderator", true);
                requestDispatcher.forward(req, resp);
            }
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to login.jsp", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("logged") == null) {
            var email = req.getParameter("email");
            var password = PasswordSHA512.SHA512Hash(req.getParameter("password"));
            try {
                SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
                Session session = sessionFactory.getCurrentSession();
                Transaction transaction = session.beginTransaction();
                Query<ModeratorAccount> query = session.createQuery("from ModeratorAccount where email = :email and password = :password", ModeratorAccount.class);
                query.setParameter("email", email);
                query.setParameter("password", password);
                var account = query.stream().findAny();
                transaction.commit();
                if (account.isPresent()) {
                    req.getSession().setAttribute("logged", "moderator");
                    req.getSession().setAttribute("moderator", account.get());
                    resp.sendRedirect("/home");
                } else {
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
                    req.setAttribute("moderator", true);
                    req.setAttribute("error", true);
                    requestDispatcher.forward(req, resp);
                }
            } catch (HibernateException | IOException | ServletException e) {
                logger.warn("Exception in doPost", e);
            }
        }


    }
}

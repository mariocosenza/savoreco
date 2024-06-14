package it.savoreco.controller;

import it.savoreco.model.entity.ModeratorAccount;
import it.savoreco.model.entity.SellerAccount;
import it.savoreco.model.entity.UserAccount;
import it.savoreco.service.CookieFactory;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebInitParam(
        name = "usernamePattern",
        value = ""
)

@WebServlet(
        name = "loginPage",
        value = "/login"
)
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL)
)
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Pattern pattern = Pattern.compile(getServletContext().getInitParameter("usernamePattern"));
        Matcher matcher = pattern.matcher(request.getParameter("username"));
        Cookie[] cookies = request.getCookies();
        boolean logged = false;
        HttpSession session = request.getSession(false);

        if (session == null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
            if (request.getParameter("radioType").equals("user")) {
                if (attemptLogin(UserAccount.class, email, password, sessionFactory)) {
                    response.addCookie(CookieFactory.newUserCookie(request.getSession()));
                    logged = true;
                }
            } else if (request.getParameter("radioType").equals("seller")) {
                if (attemptLogin(SellerAccount.class, email, password, sessionFactory)) {
                    response.addCookie(CookieFactory.newSellerCookie(request.getSession()));
                    logged = true;
                }
            } else if (request.getParameter("radioType").equals("moderator")) {
                if (attemptLogin(ModeratorAccount.class, email, password, sessionFactory)) {
                    response.addCookie(CookieFactory.newModeratorCookie(request.getSession()));
                    logged = true;
                }
            }
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    if (cookie.getValue().equals(session.getId())) {
                        logged = true;
                    }
                    break;
                } else if (cookie.getName().equals("seller")) {
                    if (cookie.getValue().equals(session.getId())) {
                        logged = true;
                    }
                    break;
                } else if (cookie.getName().equals("moderator")) {
                    if (cookie.getValue().equals(session.getId())) {
                        logged = true;
                    }
                    break;
                }

            }

        }

        if (logged) {
            response.setStatus(200);
            response.setContentType("text/html");
            try {
                var writer = response.getWriter();
                Objects.requireNonNull(writer).append("this is 200");
            } catch (IOException e) {
                logger.warn("Cannot use Writer");
            }
        } else {
            response.setStatus(404);
        }
    }

    private boolean attemptLogin(Class<?> classe, String email, String password, SessionFactory sessionFactory) {
        String hql = null;
        List<?> list = null;
        if (classe == UserAccount.class) {
            hql = "FROM UserAccount u WHERE u.email= :email AND u.password= :password";
            Query<UserAccount> query = sessionFactory.getCurrentSession().createQuery(hql, UserAccount.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            list = query.list();
        } else if (classe == SellerAccount.class) {
            hql = "FROM SellerAccount u WHERE u.email= :email AND u.password= :password";
            Query<SellerAccount> query = sessionFactory.getCurrentSession().createQuery(hql, SellerAccount.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            list = query.list();
        } else if (classe == ModeratorAccount.class) {
            hql = "FROM ModeratorAccount u WHERE u.email= :email AND u.password= :password";
            Query<ModeratorAccount> query = sessionFactory.getCurrentSession().createQuery(hql, ModeratorAccount.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            list = query.list();
        }

        return list != null && !list.isEmpty();

    }

}

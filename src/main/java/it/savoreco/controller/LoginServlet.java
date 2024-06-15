package it.savoreco.controller;

import it.savoreco.model.entity.ModeratorAccount;
import it.savoreco.model.entity.SellerAccount;
import it.savoreco.model.entity.UserAccount;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



@WebServlet(
        name = "loginPage",
        value = "/login"
)
/*
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL)
)*/
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to login.jsp", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String logged = null;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("logged") == null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");

            if (request.getParameter("radioType").equals("user")) {
                if (attemptLogin(UserAccount.class, email, password, sessionFactory)) {
                    logged = "user";
                }
            } else if (request.getParameter("radioType").equals("seller")) {
                if (attemptLogin(SellerAccount.class, email, password, sessionFactory)) {
                    logged = "seller";
                }
            } else if (request.getParameter("radioType").equals("moderator")) {
                if (attemptLogin(ModeratorAccount.class, email, password, sessionFactory)) {
                    logged = "moderator";
                }
            }
        } else if (session.getAttribute("logged") != null) {
            logged = session.getAttribute("logged").toString();
        }

        if (logged == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            request.getSession().setAttribute("logged", logged);
        }


        try (PrintWriter out = response.getWriter()) {
            out.println("{\"loginStatus\" : {\"login \": " + logged + "}}");
        } catch (IOException e) {
            logger.warn("Error writing response", e);
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

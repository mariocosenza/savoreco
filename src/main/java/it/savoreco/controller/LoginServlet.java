package it.savoreco.controller;


import it.savoreco.model.entity.SellerAccount;
import it.savoreco.model.entity.UserAccount;
import it.savoreco.service.PasswordSHA512;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;


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
        try {
            if((Objects.nonNull(request.getSession(false)) && Objects.nonNull(request.getSession(false).getAttribute("logged")))) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserAccount account = null;
        SellerAccount sellerAccount = null;


        var password = req.getParameter("password");
        var email = req.getParameter("email");


        if (Objects.nonNull(req.getParameter("profile_type"))
                && (req.getParameter("profile_type").equals("user") || req.getParameter("profile_type").equals("seller"))
                &&  Objects.nonNull(email) && Objects.nonNull(password)
                &&  (Objects.isNull(req.getSession(false)) || Objects.isNull(req.getSession().getAttribute("logged")))) {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            if(req.getParameter("profile_type").equals("user")) {
                Query<UserAccount> query = session.createQuery("FROM UserAccount u WHERE u.email= :email AND u.password= :password" , UserAccount.class);
                query.setParameter("email", email.trim());
                query.setParameter("password", PasswordSHA512.SHA512Hash(password));
                account = query.list().getFirst();
            } else {
                Query<SellerAccount> query = session.createQuery("FROM SellerAccount u WHERE u.email= :email AND u.password= :password", SellerAccount.class);
                query.setParameter("email", email.trim());
                query.setParameter("password", PasswordSHA512.SHA512Hash(password));
                sellerAccount = query.list().getFirst();
            }
            transaction.commit();
        }

        if(Objects.nonNull(account)) {
            var session = req.getSession();
            session.setAttribute("user", account);
            session.setAttribute("logged", "user");
        } else if(Objects.nonNull(sellerAccount)) {
            var session = req.getSession();
            session.setAttribute("seller", sellerAccount);
            session.setAttribute("logged", "seller");
        }  else {
            try {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
                req.setAttribute("error", true);
                requestDispatcher.forward(req, resp);
                return;
            } catch (IOException | ServletException e) {
                logger.warn("Error sending include", e);
            }
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to index.jsp", e);
        }

    }

}

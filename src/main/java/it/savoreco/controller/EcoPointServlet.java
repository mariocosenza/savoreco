package it.savoreco.controller;



import it.savoreco.model.entity.UserAccount;
import it.savoreco.service.PointLevel;
import jakarta.servlet.RequestDispatcher;
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


@WebServlet(
        name = "ecoServlet",
        displayName = "Eco Punti",
        description = "Eco Punti livello",
        value = "/user/ecopoint"
)
public class EcoPointServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(EcoPointServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/user/ecopoint.jsp");
        try {
            SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            var user = session.get(UserAccount.class, ((UserAccount) req.getSession().getAttribute("user")).getId());
            var point = user.getEcoPoint();
            transaction.commit();

            var level = PointLevel.calcolaLivello(user.getEcoPoint());
            req.setAttribute("ecoPoint", point);
            req.setAttribute("level", level);
            req.setAttribute("minEcoPoint", PointLevel.minLivello(level));
            req.setAttribute("maxEcoPoint", PointLevel.maxLivello(level));
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            logger.warn("Cannot forward to ecopoint.jsp", e);
        }
    }
}

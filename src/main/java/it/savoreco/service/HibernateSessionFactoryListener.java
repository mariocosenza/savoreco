package it.savoreco.service;

import it.savoreco.model.entity.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactoryListener.class);

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("SessionFactory");
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            logger.info("Closing sessionFactory");
            sessionFactory.close();
        }
        logger.info("Released Hibernate sessionFactory resource");
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(SellerAccount.class);
        configuration.addAnnotatedClass(Basket.class);
        configuration.addAnnotatedClass(BasketContain.class);
        configuration.addAnnotatedClass(BasketContainId.class);
        configuration.addAnnotatedClass(Food.class);
        configuration.addAnnotatedClass(ModeratorAccount.class);
        configuration.addAnnotatedClass(Purchase.class);
        configuration.addAnnotatedClass(Restaurant.class);
        configuration.addAnnotatedClass(BoughtFood.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(AddressId.class);
        configuration.addAnnotatedClass(UserAccount.class);

        logger.info("Hibernate Configuration created successfully");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        logger.info("ServiceRegistry created successfully");
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);
        logger.info("SessionFactory created successfully");

        servletContextEvent.getServletContext().setAttribute("SessionFactory", sessionFactory);
        logger.info("Hibernate SessionFactory Configured successfully");
    }

}
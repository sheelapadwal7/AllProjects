package com.test.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;

    static {
        try {
            // Initialize the SessionFactory from Hibernate configuration file
            factory = new Configuration()
                    .configure("hibernate.cfg.xml") // Load hibernate.cfg.xml
                    .addAnnotatedClass(com.test.model.Tag.class) // Register the entity classes
                    .addAnnotatedClass(com.test.model.Post.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    // Public method to get the SessionFactory
    public static SessionFactory getSessionFactory() {
        return factory;
    }

    // Optional: Method to shut down the SessionFactory at the end of the application lifecycle
    public static void shutdown() {
        getSessionFactory().close();
    }
}

package ru.job4j.gsmirnov.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The util to work with Postgres database through Hibernate.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 09/09/2019
 */
public class HibernateUtil {
    /**
     * The factory which opens sessions to work with database.
     */
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * The factory initialization.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println(String.format("Initial SessionFactory creation failed. %s", ex));
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets the initialized session factory.
     *
     * @return the initialized session factory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Close caches and connection pools.
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
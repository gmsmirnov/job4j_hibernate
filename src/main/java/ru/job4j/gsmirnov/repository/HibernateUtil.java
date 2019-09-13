package ru.job4j.gsmirnov.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The util to work with Postgres database through Hibernate.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 09/09/2019
 */
public class HibernateUtil {
    /**
     * The factory which opens sessions to work with database.
     */
    private static SessionFactory sessionFactory;

    /**
     * The factory initialization.
     *
     * @return initialized session factory.
     */
    public static SessionFactory getSessionFactory() {
        if (HibernateUtil.sessionFactory == null) {
            HibernateUtil.sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return HibernateUtil.sessionFactory;
    }
}
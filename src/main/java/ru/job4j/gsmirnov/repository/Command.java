package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 14/09/2019
 */
public class Command {
    private final static Logger LOG = LogManager.getLogger(Command.class);

    public static <T> T tx(final Function<Session, T> command) {
        Transaction transaction = null;
        T result = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = command.apply(session);
            transaction.commit();
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static void tx(final Consumer<Session> command) {
        Transaction transaction = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            command.accept(session);
            transaction.commit();
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }
}
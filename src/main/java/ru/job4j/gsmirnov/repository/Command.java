package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 14/09/2019
 */
public class Command {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(Command.class);

    /**
     * A wrapper over a command which returns a value.
     *
     * @param command the command which need to execute.
     * @param <T> the type of result.
     * @return the result of the specified command.
     */
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

    /**
     * A wrapper over a command.
     *
     * @param command the specified command to execute.
     */
    public static void tx(final Consumer<Session> command) {
        Command.tx(
                session -> {
                    command.accept(session);
                    return null;
                }
        );
    }
}
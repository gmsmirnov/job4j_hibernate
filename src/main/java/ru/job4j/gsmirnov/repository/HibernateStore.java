package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 14/09/2019
 */
public abstract class HibernateStore<E> implements Store<E> {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(HibernateStore.class);

    /**
     * A wrapper over a command which returns a value.
     *
     * @param command the command which need to execute.
     * @return the result of the specified command.
     */
    protected <R> Optional<R> tx(final Function<Session, R> command) {
        Transaction transaction = null;
        Optional<R> result = Optional.empty();
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = Optional.ofNullable(command.apply(session));
            transaction.commit();
        } catch (final Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (final Exception er) {
                    LOG.error(e.getMessage(), e);
                }
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
    protected void tx(final Consumer<Session> command) {
        this.tx(
                session -> {
                    command.accept(session);
                    return Optional.empty();
                }
        );
    }
}
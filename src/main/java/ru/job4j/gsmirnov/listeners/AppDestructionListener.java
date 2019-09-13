package ru.job4j.gsmirnov.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.gsmirnov.repository.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Used to check SessionFactory existence during application shutdown.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 11/09/2019
 */
public class AppDestructionListener implements ServletContextListener {
    private final static Logger LOG = LogManager.getLogger(AppDestructionListener.class);

    /**
     * Invokes during application context destruction.
     *
     * @param sce not used.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (HibernateUtil.getSessionFactory() != null) {
            HibernateUtil.getSessionFactory().close();
            LOG.info("SessionFactory closed.");
        }
    }
}
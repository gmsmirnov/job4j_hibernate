package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.gsmirnov.models.User;

import java.util.List;

/**
 * The class which provides access to CRUD operations with User-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 06/06/2019
 */
public class UserDAO {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(UserDAO.class);

    /**
     * Adds a new user through the hibernate API
     *
     * @param user a new user to addition into DB.
     */
    public User addUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * Finds user by id.
     *
     * @param id the specified user's id.
     * @return the user mapped to the specified id.
     */
    public User findUser(int id) {
        Transaction transaction = null;
        User result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            for (User user : users) {
                if (user.getId() == id) {
                    result = user;
                    break;
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds user by name.
     *
     * @param name the specified user's name.
     * @return the user with the specified name.
     */
    public User findUser(String name) {
        Transaction transaction = null;
        User result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            for (User user : users) {
                if (user.getName().equals(name)) {
                    result = user;
                    break;
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Updates the user with the specified id.
     *
     * @param id   the specified user's id.
     * @param user a new user's params.
     */
    public void updateUser(int id, User user) {
        user.setId(id);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Deletes the user with the specified id.
     *
     * @param id the specified user's id.
     */
    public void deleteUser(int id) {
        Transaction transaction = null;
        User user = new User();
        user.setId(id);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Finds all users in DB.
     *
     * @return the list of all users.
     */
    public List<User> findAllUsers() {
        Transaction transaction = null;
        List<User> result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.createQuery("from User").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Deletes all users from DB.
     */
    public void deleteAllUsers() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }
}
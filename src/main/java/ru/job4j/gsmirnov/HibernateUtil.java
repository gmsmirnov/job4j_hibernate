package ru.job4j.gsmirnov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.gsmirnov.models.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * Hibernate Util Class provides access to CRUD operations with User-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 06/06/2019
 */
public class HibernateUtil {
    /**
     * Adds a new user through the hibernate API
     *
     * @param factory - the specified sessions factory.
     * @param name - a new user's name.
     */
    public static void addUser(SessionFactory factory, String name) {
        User user = new User();
        user.setName(name);
        user.setExpired(new Timestamp(System.currentTimeMillis() + User.YEAR));
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Finds user by id.
     *
     * @param factory - the specified sessions factory.
     * @param id - the specified user's id.
     * @return the user mapped to the specified id.
     */
    public static User findUser(SessionFactory factory, int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        User result = null;
        List<User> users = session.createQuery("from User").list();
        for (User user : users) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Finds user by name.
     *
     * @param factory - the specified sessions factory.
     * @param name - the specified name.
     * @return the user with the specified name.
     */
    public static User findUser(SessionFactory factory, String name) {
        Session session = factory.openSession();
        session.beginTransaction();
        User result = null;
        List<User> users = session.createQuery("from User").list();
        for (User user : users) {
            if (user.getName().equals(name)) {
                result = user;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Updates the name of user with the specified id.
     *
     * @param factory - the specified sessions factory.
     * @param id - the specified user's id.
     * @param name - a new name for the user with the specified id.
     */
    public static void updateUser(SessionFactory factory, int id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setExpired(new Timestamp(System.currentTimeMillis() + User.YEAR));
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Deletes the user with the specified id.
     *
     * @param factory - the specified sessions factory.
     * @param id - the specified user's id.
     */
    public static void deleteUser(SessionFactory factory, int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setId(id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Finds all users in DB.
     *
     * @param factory - the specified sessions factory.
     * @return the list of all users.
     */
    public static List<User> findAllUsers(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    /**
     * Deletes all users from DB.
     *
     * @param factory - the specified sessions factory.
     */
    public static void deleteAllUsers(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery("delete from users").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
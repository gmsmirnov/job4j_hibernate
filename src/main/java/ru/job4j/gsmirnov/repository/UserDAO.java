package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.gsmirnov.models.User;

import java.util.List;

/**
 * The class which provides access to CRUD operations with User-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.3
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
        return Command.tx(
                session -> {
                    session.save(user);
                    return user;
                }
        );
    }

    /**
     * Finds user by id.
     *
     * @param id the specified user's id.
     * @return the user mapped to the specified id.
     */
    public User findUser(int id) {
        return Command.tx(
                session -> {
                    return session.get(User.class, id);
                }
        );
    }

    /**
     * Finds user by name.
     *
     * @param name the specified user's name.
     * @return the user with the specified name.
     */
    public User findUser(String name) {
        return Command.tx(
                session -> {
                    Query<User> query = session.createQuery("from User as user where user.name =: name", User.class);
                    query.setParameter("name", name);
                    return query.list().get(0); // name is unique
                }
        );
    }

    /**
     * Updates the user with the specified id.
     *
     * @param id   the specified user's id.
     * @param user a new user's params.
     */
    public void updateUser(int id, User user) {
        user.setId(id);
        Command.tx(
                session -> {
                    session.update(user);
                }
        );
    }

    /**
     * Deletes the user with the specified id.
     *
     * @param id the specified user's id.
     */
    public void deleteUser(int id) {
        User user = new User();
        user.setId(id);
        Command.tx(
                session -> {
                    session.delete(user);
                }
        );
    }

    /**
     * Finds all users in DB.
     *
     * @return the list of all users.
     */
    public List<User> findAllUsers() {
        return Command.tx(
                session -> {
                    return session.createQuery("from User", User.class).list();
                }
        );
    }

    /**
     * Deletes all users from DB.
     */
    public void deleteAllUsers() {
        Command.tx(
                session -> {
                    session.createSQLQuery("delete from users").executeUpdate();
                }
        );
    }
}
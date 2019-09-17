package ru.job4j.gsmirnov.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.gsmirnov.exception.business.NoSuchIdException;
import ru.job4j.gsmirnov.exception.business.NoSuchModelException;
import ru.job4j.gsmirnov.exception.business.NullArgumentException;
import ru.job4j.gsmirnov.models.User;
import ru.job4j.gsmirnov.repository.UserDAO;

import java.util.List;

/**
 * The user validator. Check params and returned values.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 09/09/2019
 */
public class UserValidator implements BaseValidator {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(UserValidator.class);

    /**
     * The user validator. Singleton.
     */
    private static final UserValidator USER_VALIDATOR = new UserValidator();

    /**
     * The data access object for user model.
     */
    private final UserDAO userDAO;

    /**
     * The default constructor.
     */
    private UserValidator() {
        this.userDAO = new UserDAO();
    }

    /**
     * Gets the user's validator which checks params an returned values.
     *
     * @return the user's validator.
     */
    public static UserValidator getUserValidator() {
        return UserValidator.USER_VALIDATOR;
    }

    /**
     * Checks the user for null and puts it into database.
     *
     * @param user the specified user.
     * @throws NullArgumentException if the specified user param is null.
     */
    @Override
    public User addEntity(Object user) throws NullArgumentException {
        this.checkEntity((User) user);
        return this.userDAO.addUser((User) user);
    }

    /**
     * Finds the user by the specified id and checks the result for null.
     *
     * @param id the specified id.
     * @return the user which is mapped to the specified id. Never returns null.
     * @throws NoSuchIdException if the result is null.
     */
    @Override
    public User findEntity(int id) throws NoSuchIdException {
        User result = this.userDAO.findUser(id);
        if (result == null) {
            throw new NoSuchIdException("There is no user with such id in database.");
        }
        return result;
    }

    /**
     * Checks id for existence, user for null and updates the user with the specified id.
     *
     * @param id   the specified user's id.
     * @param user new user's params.
     * @throws NullArgumentException if the specified user param is null.
     */
    @Override
    public void updateEntity(int id, Object user) throws NoSuchIdException, NullArgumentException {
        if (this.userDAO.findUser(id) == null) {
            throw new NoSuchIdException("There is no user with such id.");
        }
        this.checkEntity((User) user);
        this.userDAO.updateUser(id, (User) user);
    }

    /**
     * Checks the id for existence and deletes the user with the specified id.
     *
     * @param id the specified id.
     * @throws NoSuchIdException if there is no user with such id.
     */
    @Override
    public void deleteEntity(int id) throws NoSuchIdException {
        if (this.userDAO.findUser(id) == null) {
            throw new NoSuchIdException("There is no user with such id.");
        }
        this.userDAO.deleteUser(id);
    }

    /**
     * Finds all users in database.
     *
     * @return the list of all users.
     * @throws NoSuchModelException if the list is null or is empty.
     */
    @Override
    public List<User> findAllEntities() throws NoSuchModelException {
        List<User> result = this.userDAO.findAllUsers();
        if (result == null) {
            throw new NoSuchModelException("The result list is null.");
        }
        return result;
    }

    /**
     * Deletes all users from database.
     */
    @Override
    public void deleteAllEntities() {
        this.userDAO.deleteAllUsers();
    }

    /**
     * Finds the user with the specified name.
     *
     * @param name the specified name.
     * @return the user with the specified name.
     * @throws NoSuchModelException if there is no user with such name in database.
     */
    public User findUser(String name) throws NoSuchModelException {
        User result = this.userDAO.findUser(name);
        if (result == null) {
            throw new NoSuchModelException("There is no user with such name in database.");
        }
        return result;
    }

    /**
     * Checks the specified argument is not null.
     *
     * @param user the specified user.
     * @throws NullArgumentException if the specified param is null.
     */
    private void checkEntity(User user) throws NullArgumentException {
        if (user == null) {
            throw new NullArgumentException("Incorrect argument: user.");
        }
    }
}
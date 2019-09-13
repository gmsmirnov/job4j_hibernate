package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.gsmirnov.exception.business.NoSuchIdException;
import ru.job4j.gsmirnov.exception.business.NoSuchModelException;
import ru.job4j.gsmirnov.exception.business.NullArgumentException;
import ru.job4j.gsmirnov.logic.UserValidator;
import ru.job4j.gsmirnov.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Testing the Hibernate Util Class which provides access to CRUD operations with User-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 06/06/2019
 */
public class UserValidateDAOTest {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(UserValidateDAOTest.class);

    /**
     * The session factory.
     */
    private UserValidator userValidator = UserValidator.getUserValidator();

    /**
     * Empties user's table before every test.
     */
    @Before
    public void init() {
        this.userValidator.deleteAllEntities();
    }

    /**
     * Empties the user's table after each test.
     */
    @After
    public void close() {
        this.userValidator.deleteAllEntities();
    }

    /**
     * Adding a new user, getting this user.
     */
    @Test
    public void whenAddUserThenTrue() throws NullArgumentException, NoSuchModelException {
        User user = new User(1, "Admin");
        this.userValidator.addEntity(user);
        User result = this.userValidator.findUser("Admin");
        assertThat(user.getName(), is("Admin"));
        assertThat(this.userValidator.findAllEntities().get(0).getName(), is("Admin"));
        assertThat(this.userValidator.findAllEntities().get(0).getId(), is(result.getId()));
    }

    /**
     * Adding a new user, updating this user.
     */
    @Test
    public void whenUpdateUserThenTrue() throws NullArgumentException, NoSuchModelException, NoSuchIdException {
        User user = new User();
        user.setName("Admin");
        this.userValidator.addEntity(user);
        user = this.userValidator.findUser("Admin");
        user.setName("Superadmin");
        this.userValidator.updateEntity(user.getId(), user);
        assertThat(this.userValidator.findEntity(user.getId()).getName(), is("Superadmin"));
    }

    /**
     * Adding a new user, deleting this user, checking DB.
     */
    @Test
    public void whenDeleteThenTrue() throws NullArgumentException, NoSuchModelException, NoSuchIdException {
        User user = new User();
        user.setName("Admin");
        this.userValidator.addEntity(user);
        assertThat(this.userValidator.findAllEntities().size(), is(1));
        int id = this.userValidator.findUser("Admin").getId();
        this.userValidator.deleteEntity(id);
        assertThat(this.userValidator.findAllEntities().size(), is(0));
    }

    /**
     * Exception when no specified id.
     */
    @Test (expected = NoSuchIdException.class)
    public void whenNoSpecifiedIdThenException() throws NoSuchIdException {
        this.userValidator.findEntity(100);
    }

}
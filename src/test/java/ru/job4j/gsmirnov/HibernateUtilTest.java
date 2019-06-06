package ru.job4j.gsmirnov;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.gsmirnov.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Testing the Hibernate Util Class which provides access to CRUD operations with User-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 06/06/2019
 */
public class HibernateUtilTest {
    /**
     * The session factory.
     */
    private SessionFactory factory;

    /**
     * Creates a new factory, empties user's table before every test.
     */
    @Before
    public void init() {
        this.factory = new Configuration().configure().buildSessionFactory();
        HibernateUtil.deleteAllUsers(this.factory);
    }

    /**
     * Closes factory after every test.
     */
    @After
    public void close() {
        this.factory.close();
    }

    /**
     * Adding a new user, getting this user.
     */
    @Test
    public void whenAddUserThenTrue() {
        HibernateUtil.addUser(this.factory, "Admin");
        User user = HibernateUtil.findUser(this.factory, "Admin");
        assertThat(user.getName(), is("Admin"));
        assertThat(HibernateUtil.findAllUsers(this.factory).get(0).getName(), is("Admin"));
        assertThat(HibernateUtil.findAllUsers(this.factory).get(0).getId(), is(user.getId()));
    }

    /**
     * Adding a new user, updating this user.
     */
    @Test
    public void whenUpdateUserThenTrue() {
        HibernateUtil.addUser(this.factory, "Admin");
        User user = HibernateUtil.findUser(this.factory, "Admin");
        HibernateUtil.updateUser(this.factory, user.getId(), "Superadmin");
        assertThat(HibernateUtil.findUser(this.factory, user.getId()).getName(), is("Superadmin"));
    }

    /**
     * Adding a new user, deleting this user, checking DB.
     */
    @Test
    public void whenDeleteThenTrue() {
        HibernateUtil.addUser(this.factory, "Admin");
        assertThat(HibernateUtil.findAllUsers(this.factory).size(), is(1));
        int id = HibernateUtil.findUser(this.factory, "Admin").getId();
        HibernateUtil.deleteUser(this.factory, id);
        assertThat(HibernateUtil.findAllUsers(this.factory).size(), is(0));
    }
}
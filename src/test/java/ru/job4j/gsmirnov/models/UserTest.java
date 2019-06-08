package ru.job4j.gsmirnov.models;

import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Testing the User model.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/06/2019
 */
public class UserTest {
    @Test
    public void whenTwoUsersAreEqualsThenTrue() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Admin");
        user1.setExpired(new Timestamp(System.currentTimeMillis() + User.YEAR));
        User user2 = new User();
        user2.setId(2);
        user2.setName("Admin");
        user2.setExpired(new Timestamp(System.currentTimeMillis() + User.YEAR));
        assertThat(user1.equals(user2), is(true));
    }

    @Test
    public void whenTwoUsersAreNotEqualsThenFalse() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Admin");
        user1.setExpired(new Timestamp(System.currentTimeMillis() + User.YEAR));
        User user2 = new User();
        user2.setId(1);
        user2.setName("User");
        user2.setExpired(new Timestamp(System.currentTimeMillis() + User.YEAR));
        assertThat(user1.equals(user2), is(false));
    }
}
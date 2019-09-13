package ru.job4j.gsmirnov.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Testing the Task model.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 12/09/2019
 */
public class TaskTest {
    private final static Logger LOG = LogManager.getLogger(TaskTest.class);

    @Test
    public void whenTwoTasksAreEqualsThenTrue() {
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("task 1");
        task1.setDesc("very big task 1");
        task1.setCreated(new Timestamp(System.currentTimeMillis()));
        task1.setDone(true);
        Task task2 = new Task();
        task2.setId(2);
        task2.setName("task 1");
        task2.setDesc("very big task 1");
        task2.setCreated(new Timestamp(System.currentTimeMillis()));
        task2.setDone(false);
        assertThat(task1.equals(task2), is(true));
    }

    @Test
    public void whenTwoUsersAreNotEqualsThenFalse() {
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("task 1");
        task1.setDesc("very big task 1");
        task1.setCreated(new Timestamp(System.currentTimeMillis()));
        task1.setDone(true);
        Task task2 = new Task();
        task2.setId(2);
        task2.setName("task 2");
        task2.setDesc("very big task 2");
        task2.setCreated(new Timestamp(System.currentTimeMillis()));
        task2.setDone(false);
        assertThat(task1.equals(task2), is(false));
    }

    @Test
    public void whenTwoBaseEntityUsersAreNotEqualsThenFalse() {
        BaseEntity task1 = new Task();
        BaseEntity task2 = new Task("task 1", "desc 1", new Timestamp(System.currentTimeMillis()), false);
        assertThat(task1.equals(task2), is(false));
    }

    @Test
    public void whenTwoBaseEntityUsersAreEqualsThenTrue() {
        BaseEntity task1 = new Task();
        BaseEntity task2 = new Task();
        assertThat(task1.equals(task2), is(true));
    }

}
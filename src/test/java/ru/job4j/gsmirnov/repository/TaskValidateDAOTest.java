package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.gsmirnov.exception.business.NoSuchIdException;
import ru.job4j.gsmirnov.exception.business.NoSuchModelException;
import ru.job4j.gsmirnov.exception.business.NullArgumentException;
import ru.job4j.gsmirnov.logic.TaskValidator;
import ru.job4j.gsmirnov.models.Task;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing the Hibernate Util Class which provides access to CRUD operations with Task-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 06/06/2019
 */
public class TaskValidateDAOTest {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(TaskValidateDAOTest.class);

    /**
     * The session factory.
     */
    private TaskValidator taskValidator = TaskValidator.getTaskValidator();

    /**
     * Empties tasks's table before every test.
     */
    @Before
    public void init() {
        this.taskValidator.deleteAllEntities();
    }

    /**
     * Empties the tasks's table after each test.
     */
    @After
    public void close() {
        this.taskValidator.deleteAllEntities();
    }

    /**
     * Adding a new task, database contains this task.
     */
    @Test
    public void whenAddTaskThenTrue() throws NullArgumentException, NoSuchModelException {
        Task task = new Task("task 1", "desc 1", new Timestamp(System.currentTimeMillis()), false);
        assertThat(this.taskValidator.findTasks("task 1").contains(task), is(false));
        assertThat(this.taskValidator.findAllEntities().contains(task), is(false));
        this.taskValidator.addEntity(task);
        assertThat(task.getName(), is("task 1"));
        assertThat(this.taskValidator.findTasks("task 1").contains(task), is(true));
        assertThat(this.taskValidator.findAllEntities().contains(task), is(true));
    }

    /**
     * Adding a new task, updating this task.
     */
    @Test
    public void whenUpdateUserThenTrue() throws NullArgumentException, NoSuchModelException, NoSuchIdException {
        Task task = new Task();
        task.setName("task 1");
        this.taskValidator.addEntity(task);
        assertThat(this.taskValidator.findTasks("task 1").size(), is(1));
        task = this.taskValidator.findTasks("task 1").get(0);
        task.setName("super task");
        this.taskValidator.updateEntity(task.getId(), task);
        assertThat(this.taskValidator.findEntity(task.getId()).getName(), is("super task"));
    }

    /**
     * Adding a new task, deleting this task, checking DB.
     */
    @Test
    public void whenDeleteThenTrue() throws NullArgumentException, NoSuchModelException, NoSuchIdException {
        Task task = new Task();
        task.setName("task");
        assertThat(this.taskValidator.findAllEntities().size(), is(0));
        this.taskValidator.addEntity(task);
        assertThat(this.taskValidator.findAllEntities().size(), is(1));
        int id = this.taskValidator.findTasks("task").get(0).getId();
        this.taskValidator.deleteEntity(id);
        assertThat(this.taskValidator.findAllEntities().size(), is(0));
    }

    /**
     * Exception when no specified id.
     */
    @Test (expected = NoSuchIdException.class)
    public void whenNoSpecifiedIdThenException() throws NoSuchIdException {
        this.taskValidator.findEntity(100);
    }

}
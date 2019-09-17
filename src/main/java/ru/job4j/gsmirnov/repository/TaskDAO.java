package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ru.job4j.gsmirnov.models.Task;

import java.util.List;

/**
 * The class which provides access to CRUD operations with Task-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 08/09/2019
 */
public class TaskDAO {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(TaskDAO.class);

    /**
     * Adds a new task through the hibernate API
     *
     * @param task a new task
     */
    public Task addTask(Task task) {
        return Command.tx(
                session -> {
                    session.save(task);
                    return task;
                }
        );
    }

    /**
     * Finds task by id.
     *
     * @param id the specified task's id.
     * @return the task mapped to the specified id.
     */
    public Task findTask(int id) {
        return Command.tx(
                session -> {
                    return session.get(Task.class, id);
                }
        );
    }

    /**
     * Finds tasks by name.
     *
     * @param name the specified task's name.
     * @return the list of tasks with the specified name.
     */
    public List<Task> findTasks(String name) {
        return Command.tx(
                session -> {
                    final Query<Task> query = session.createQuery("from Task as task where task.name =: name", Task.class);
                    query.setParameter("name", name);
                    return query.list();
                }
        );
    }

    /**
     * Updates the task with the specified id.
     *
     * @param id   the specified task's id.
     * @param task the new specified task's params.
     */
    public void updateTask(int id, Task task) {
        task.setId(id);
        Command.tx(
                session -> {
                    session.update(task);
                }
        );
    }

    /**
     * Deletes the task with the specified id.
     *
     * @param id the specified task's id.
     */
    public void deleteTask(int id) {
        Command.tx(
                session -> {
                    Task task = new Task();
                    task.setId(id);
                    session.delete(task);
                }
        );
    }

    /**
     * Finds all tasks in DB.
     *
     * @return the list of all tasks.
     */
    public List<Task> findAllTasks() {
        return Command.tx(
                session -> {
                    return session.createQuery("from Task", Task.class).list();
                }
        );
    }

    /**
     * Deletes all tasks from DB.
     */
    public void deleteAllTasks() {
        Command.tx(
                session -> {
                    session.createSQLQuery("delete from tasks").executeUpdate();
                }
        );
    }
}
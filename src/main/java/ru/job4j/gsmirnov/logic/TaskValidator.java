package ru.job4j.gsmirnov.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.gsmirnov.exception.business.NoSuchIdException;
import ru.job4j.gsmirnov.exception.business.NoSuchModelException;
import ru.job4j.gsmirnov.exception.business.NullArgumentException;
import ru.job4j.gsmirnov.models.Task;
import ru.job4j.gsmirnov.repository.TaskDAO;

import java.util.List;

/**
 * Tasks validator. Checks params and returned values.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 09/09/2019
 */
public class TaskValidator implements BaseValidator {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(TaskValidator.class);

    /**
     * The task validator. Singleton.
     */
    private static final TaskValidator TASK_VALIDATOR = new TaskValidator();

    /**
     * The data access object for task model.
     */
    private TaskDAO taskDAO;

    /**
     * The default constructor.
     */
    private TaskValidator() {
        this.taskDAO = new TaskDAO();
    }

    /**
     * Gets the user's validator which checks params an returned values.
     *
     * @return the user's validator.
     */
    public static TaskValidator getTaskValidator() {
        return TaskValidator.TASK_VALIDATOR;
    }

    /**
     * Checks the task for null and puts it into database.
     *
     * @param task the specified task.
     * @throws NullArgumentException if the specified task param is null.
     */
    @Override
    public Task addEntity(Object task) throws NullArgumentException {
        this.checkEntity((Task) task);
        return this.taskDAO.addTask((Task) task);
    }

    /**
     * Finds the task by the specified id and checks the result for null.
     *
     * @param id the specified id.
     * @return the task which is mapped to the specified id. Never returns null.
     * @throws NoSuchIdException if the result is null.
     */
    @Override
    public Task findEntity(int id) throws NoSuchIdException {
        Task result = this.taskDAO.findTask(id);
        if (result == null) {
            throw new NoSuchIdException("There is no task with such id in database.");
        }
        return result;
    }

    /**
     * Checks id for existence, task for null and updates the task with the specified id.
     *
     * @param id   the specified task's id.
     * @param task new task's params.
     * @throws NullArgumentException if the specified task param is null.
     */
    @Override
    public void updateEntity(int id, Object task) throws NoSuchIdException, NullArgumentException {
        if (this.taskDAO.findTask(id) == null) {
            throw new NoSuchIdException("There is no user with such id.");
        }
        this.checkEntity((Task) task);
        this.taskDAO.updateTask(id, (Task) task);
    }

    /**
     * Checks the id for existence and deletes the task with the specified id.
     *
     * @param id the specified id.
     * @throws NoSuchIdException if there is no task with such id.
     */
    @Override
    public void deleteEntity(int id) throws NoSuchIdException {
        if (this.taskDAO.findTask(id) == null) {
            throw new NoSuchIdException("There is no task with such id.");
        }
        this.taskDAO.deleteTask(id);
    }

    /**
     * Finds all tasks in database.
     *
     * @return the list of all tasks.
     * @throws NoSuchModelException if the list is null or is empty.
     */
    @Override
    public List<Task> findAllEntities() throws NoSuchModelException {
        List<Task> result = this.taskDAO.findAllTasks();
        if (result == null) {
            throw new NoSuchModelException("The result list is null.");
        }
        return result;
    }

    /**
     * Deletes all tasks from database.
     */
    @Override
    public void deleteAllEntities() {
        this.taskDAO.deleteAllTasks();
    }

    /**
     * Finds the task with the specified name.
     *
     * @param name the specified name.
     * @return the list of tasks with the specified name.
     * @throws NoSuchModelException if there is no task with such name in database.
     */
    public List<Task> findTasks(String name) throws NoSuchModelException {
        List<Task> result = this.taskDAO.findTasks(name);
        if (result == null) {
            throw new NoSuchModelException("The result list is null.");
        }
        return result;
    }

    /**
     * Checks the specified argument is not null.
     *
     * @param task the specified task.
     * @throws NullArgumentException if the specified param is null.
     */
    private void checkEntity(Task task) throws NullArgumentException {
        if (task == null) {
            throw new NullArgumentException("Incorrect argument: task.");
        }
    }
}
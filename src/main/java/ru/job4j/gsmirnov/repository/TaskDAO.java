package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.gsmirnov.models.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * The class which provides access to CRUD operations with Task-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
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
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return task;
    }

    /**
     * Finds task by id.
     *
     * @param id the specified task's id.
     * @return the task mapped to the specified id.
     */
    public Task findTask(int id) {
        Transaction transaction = null;
        Task result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Task> tasks = session.createQuery("from Task").list();
            for (Task task : tasks) {
                if (task.getId() == id) {
                    result = task;
                    break;
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds tasks by name.
     *
     * @param name the specified task's name.
     * @return the list of tasks with the specified name.
     */
    public List<Task> findTask(String name) {
        Transaction transaction = null;
        List<Task> result = new LinkedList<Task>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Task> tasks = session.createQuery("from Task").list();
            for (Task task : tasks) {
                if (task.getName().equals(name)) {
                    result.add(task);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Updates the task with the specified id.
     *
     * @param id   the specified task's id.
     * @param task the new specified task's params.
     */
    public void updateTask(int id, Task task) {
        Transaction transaction = null;
        task.setId(id);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Deletes the task with the specified id.
     *
     * @param id the specified task's id.
     */
    public void deleteTask(int id) {
        Transaction transaction = null;
        Task task = new Task();
        task.setId(id);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Finds all tasks in DB.
     *
     * @return the list of all tasks.
     */
    public List<Task> findAllTasks() {
        Transaction transaction = null;
        List<Task> tasks = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            LOG.info("Начало транзакции");
            tasks = session.createQuery("from Task").list();
            LOG.info("Получили список " + tasks);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return tasks;
    }

    /**
     * Deletes all tasks from DB.
     */
    public void deleteAllTasks() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from tasks").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Finds the list of tasks with the specified name.
     *
     * @param name the specified tasks name.
     * @return the list of tasks with the specified name.
     */
    public List<Task> findTasks(String name) {
        Transaction transaction = null;
        List<Task> result = new LinkedList<Task>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Task> tasks = session.createQuery("from Task").list();
            for (Task task : tasks) {
                if (task.getName().equals(name)) {
                    result.add(task);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
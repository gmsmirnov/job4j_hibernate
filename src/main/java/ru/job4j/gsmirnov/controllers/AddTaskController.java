package ru.job4j.gsmirnov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.gsmirnov.exception.business.NullArgumentException;
import ru.job4j.gsmirnov.logic.TaskValidator;
import ru.job4j.gsmirnov.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * A new task addition controller.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 11/09/2019
 */
public class AddTaskController extends HttpServlet {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(AddTaskController.class);

    /**
     * The logic singleton instance.
     */
    private final TaskValidator taskValidator = TaskValidator.getTaskValidator();

    /**
     * Post request handler. Puts a new task into database.
     *
     * @param req HTTP request with a new task.
     * @param resp HTTP response, forwards to TasksListController.
     * @throws ServletException throws.
     * @throws IOException throws
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(String.format("Received POST request for task creation: name={%s}, description={%s}", req.getParameter("name"), req.getParameter("description")));
        try {
            Task newTask = new Task(
                    req.getParameter("name"),
                    req.getParameter("description"),
                    new Timestamp(System.currentTimeMillis()),
                    false
            );
            newTask = this.taskValidator.addEntity(newTask);
            Utils.prepareResponse(newTask, resp);
        } catch (NullArgumentException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

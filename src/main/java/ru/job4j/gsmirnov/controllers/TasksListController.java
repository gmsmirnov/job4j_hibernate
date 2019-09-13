package ru.job4j.gsmirnov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.gsmirnov.exception.business.NoSuchModelException;
import ru.job4j.gsmirnov.logic.TaskValidator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Tasks list controller. Provides the list of all tasks.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 08/09/2019
 */
public class TasksListController extends HttpServlet {
    /**
     * The logger.
     */
    private static final Logger LOG = LogManager.getLogger(TasksListController.class.getName());

    /**
     * The logic singleton instance.
     */
    private final TaskValidator taskValidator = TaskValidator.getTaskValidator();

    /**
     * Get request handler. Finds all elements in database.
     *
     * @param req HTTP request for all tasks.
     * @param resp HTTP response, contained all tasks.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOG.info("Received GET request for tasks list");
        try {
            Utils.prepareResponse(this.taskValidator.findAllEntities(), resp);
        } catch (NoSuchModelException e) {
            TasksListController.LOG.error("No such model in database.", e);
        } catch (IOException e) {
            TasksListController.LOG.error("IO error occurs.", e);
        }
    }
}
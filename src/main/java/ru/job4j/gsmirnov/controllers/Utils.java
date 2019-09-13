package ru.job4j.gsmirnov.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * Utils for controllers.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 12/09/2019
 */
public class Utils {
    private final static Logger LOG = LogManager.getLogger(Utils.class);

    /**
     * Prepares HTTP-response, puts into it json-array of all tasks.
     *
     * @param e the new element, needed to convert to JSON.
     * @param resp HTTP response.
     * @throws IOException when PrintWriter error occurs.
     */
    public static <E> void prepareResponse(E e, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=windows-1251");
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        mapper.setDateFormat(df);
        LOG.info(String.format("JSON tasks list: %s", mapper.writeValueAsString(e)));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(mapper.writeValueAsString(e));
        writer.flush();
    }
}

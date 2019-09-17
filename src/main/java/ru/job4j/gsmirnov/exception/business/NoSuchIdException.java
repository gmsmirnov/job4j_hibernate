package ru.job4j.gsmirnov.exception.business;

/**
 * There is no such id in the storage.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 30/04/2019
 */
public class NoSuchIdException extends DaoBusinessException {
    /**
     * Creates checked exception, based on message.
     *
     * @param message - the specified message.
     */
    public NoSuchIdException(String message) {
        super(message);
    }

    /**
     * Creates checked exception, based on another checked exception and message.
     *
     * @param message - the specified message.
     * @param cause - another checked exception.
     */
    public NoSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
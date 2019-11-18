package ru.job4j.gsmirnov.repository;

import java.util.List;

/**
 * Base store methods.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 01/10/2019
 */
public interface Store<E> {
    /**
     * Puts a new entity into database.
     *
     * @param entity the specified entity.
     * @return the specified entity with given by database id.
     */
    E add(E entity);

    /**
     * Updates the specifies entity.
     *
     * @param entity the specified entity with updated params (except id).
     * @return the specified updated entity.
     */
    E update(E entity);

    /**
     * Deletes the entity with the specified id from database.
     *
     * @param id the specified id.
     */
    void delete(int id);

    /**
     * Finds the entity with the specified id.
     *
     * @param id the specified id.
     * @return the entity which is mapped to the specified id.
     */
    E findById(int id);

    /**
     * Gets the list of all entities from database.
     *
     * @return the list of all entities.
     */
    List<E> findAll();

    /**
     * Deletes all entities from database.
     */
    void deleteAll();
}
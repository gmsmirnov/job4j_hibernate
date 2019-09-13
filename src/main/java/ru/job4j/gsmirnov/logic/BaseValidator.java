package ru.job4j.gsmirnov.logic;

import ru.job4j.gsmirnov.exception.business.NoSuchIdException;
import ru.job4j.gsmirnov.exception.business.NoSuchModelException;
import ru.job4j.gsmirnov.exception.business.NullArgumentException;

import java.util.List;

/**
 * A base validate layer. Verify params and returned values between view-layer and storage-layer.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 29/04/2019
 */
public interface BaseValidator<E> {
    /**
     * Puts a new entity into database.
     *
     * @param entity the specified entity.
     * @throws NullArgumentException if the specified entity param is null.
     */
    E addEntity(E entity) throws NullArgumentException;

    /**
     * Finds the entity with the specified id in database.
     *
     * @param id the specified entity's id.
     * @return the entity mapped to this id.
     * @throws NoSuchIdException if there is no entity with such id in database.
     */
    E findEntity(int id) throws NoSuchIdException;

    /**
     * Updates the entity with the specified id in database.
     *
     * @param id     the specified id.
     * @param entity the new entity's params.
     * @throws NoSuchIdException     if there is no entity with such id in database.
     * @throws NullArgumentException if the new entity's params ware null.
     */
    void updateEntity(int id, E entity) throws NoSuchIdException, NullArgumentException;

    /**
     * Deletes the entity with the specified id from database.
     *
     * @param id the specified id
     * @throws NoSuchIdException if there is no entity with sucj id in database.
     */
    void deleteEntity(int id) throws NoSuchIdException;

    /**
     * Finds all entities in database.
     *
     * @return the list of all entities.
     * @throws NoSuchModelException if the list is null or empty.
     */
    List<E> findAllEntities() throws NoSuchModelException;

    /**
     * Deletes all entities from database.
     */
    void deleteAllEntities();
}
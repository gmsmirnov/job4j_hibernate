package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ru.job4j.gsmirnov.models.Body;

import java.util.ArrayList;
import java.util.List;

/**
 * The class which provides access to CRUD operations with {@link Body}-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class BodyRepository extends HibernateStore<Body> {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(BodyRepository.class);

    /**
     * Puts the specified body object to the database.
     *
     * @param body the specified body object.
     * @return added body object (with given by database id).
     */
    @Override
    public Body add(Body body) {
        return this.tx(session -> {
          session.save(body);
          return body;
        }).orElse(new Body());
    }

    /**
     * Updates the specified body.
     *
     * @param body the specified body object with updated params (except id).
     * @return updated body object.
     */
    @Override
    public Body update(Body body) {
        return this.tx(session -> {
            session.update(body);
            return body;
        }).orElse(new Body());
    }

    /**
     * Deletes the body with the specified id from the database.
     *
     * @param id the specified id.
     */
    @Override
    public void delete(int id) {
        this.tx(session -> {
           session.delete(new Body(id));
        });
    }

    /**
     * Finds the body by the specified id.
     *
     * @param id the specified id.
     * @return the body which is mapped to the specified id.
     */
    @Override
    public Body findById(int id) {
        return this.tx(session -> {
            return session.find(Body.class, id);
        }).orElse(new Body());
    }

    /**
     * Gets all body's list from database.
     *
     * @return the list of all bodies.
     */
    @Override
    public List<Body> findAll() {
        return this.tx(session -> {
            final Query<Body> query = session.createQuery("from Body", Body.class);
            return query.list();
        }).orElse(new ArrayList<Body>());
    }

    /**
     * Deletes all bodies from database.
     */
    @Override
    public void deleteAll() {
        this.tx(session -> {
            session.createQuery("delete Body").executeUpdate();
        });
    }

    /**
     * Finds a list of bodies by name.
     *
     * @param name the specified body's name.
     * @return the list of bodies with the specified name.
     */
    public List<Body> findBodyByName(String name) {
        return this.tx(session -> {
            final Query<Body> query = session.createQuery("from Body as body where body.name =: name", Body.class);
            query.setParameter("name", name);
            return query.list(); // name is not unique
        }).orElse(new ArrayList<Body>());
    }

    /**
     * Finds a body by its serial number.
     *
     * @param serialNumber the specified body's serial number.
     * @return the body with the specified serial number.
     */
    public Body findBodyBySerialNumber(String serialNumber) {
        return this.tx(session -> {
            final Query<Body> query = session.createQuery("from Body as body where body.serialNumber =: serial_number", Body.class);
            query.setParameter("serial_number", serialNumber);
            return query.list().get(0); // s/n is unique
        }).orElse(new Body());
    }
}
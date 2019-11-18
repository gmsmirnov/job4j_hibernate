package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ru.job4j.gsmirnov.models.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * The class which provides access to CRUD operations with {@link Engine}-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class EngineRepository extends HibernateStore<Engine> {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(EngineRepository.class);

    /**
     * Puts the specified engine object to the database.
     *
     * @param engine the specified engine object.
     * @return added engine object (with given by database id).
     */
    @Override
    public Engine add(Engine engine) {
        return this.tx(session -> {
            session.save(engine);
            return engine;
        }).orElse(new Engine());
    }

    /**
     * Updates the specified engine.
     *
     * @param engine the specified engine object with updated params (except id).
     * @return updated engine object.
     */
    @Override
    public Engine update(Engine engine) {
        return this.tx(session -> {
            session.update(engine);
            return engine;
        }).orElse(new Engine());
    }

    /**
     * Deletes the engine with the specified id from the database.
     *
     * @param id the specified id.
     */
    @Override
    public void delete(int id) {
        this.tx(session -> {
            session.delete(new Engine(id));
        });
    }

    /**
     * Finds the engine by the specified id.
     *
     * @param id the specified id.
     * @return the engine which is mapped to the specified id.
     */
    @Override
    public Engine findById(int id) {
        return this.tx(session -> {
            return session.find(Engine.class, id);
        }).orElse(new Engine());
    }

    /**
     * Gets all engines list from database.
     *
     * @return the list of all engines.
     */
    @Override
    public List<Engine> findAll() {
        return this.tx(session -> {
            final Query<Engine> query = session.createQuery("from Engine", Engine.class);
            return query.list();
        }).orElse(new ArrayList<Engine>());
    }

    /**
     * Deletes all engines from database.
     */
    @Override
    public void deleteAll() {
        this.tx(session -> {
            session.createQuery("delete Engine").executeUpdate();
        });
    }

    /**
     * Finds a list of engines by name.
     *
     * @param name the specified engine's name.
     * @return the list of engines with the specified name.
     */
    public List<Engine> findEnginesByName(String name) {
        return this.tx(session -> {
            final Query<Engine> query = session.createQuery("from Engine as engine where engine.name =: name", Engine.class);
            query.setParameter("name", name);
            return query.list(); // name is not unique
        }).orElse(new ArrayList<Engine>());
    }

    /**
     * Finds an engine by its serial number.
     *
     * @param serialNumber the specified engine's serial number.
     * @return the engine with the specified serial number.
     */
    public Engine findEngineBySerialNumber(String serialNumber) {
        return this.tx(session -> {
            final Query<Engine> query = session.createQuery("from Engine as engine where engine.serialNumber =: serial_number", Engine.class);
            query.setParameter("serial_number", serialNumber);
            return query.list().get(0); // s/n is unique
        }).orElse(new Engine());
    }
}
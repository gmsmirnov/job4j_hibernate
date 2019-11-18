package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ru.job4j.gsmirnov.models.Transmission;

import java.util.ArrayList;
import java.util.List;

/**
 * The class which provides access to CRUD operations with {@link Transmission}-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class TransmissionRepository extends HibernateStore<Transmission> {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(TransmissionRepository.class);

    /**
     * Puts the specified transmission object to the database.
     *
     * @param transmission the specified transmission object.
     * @return added transmission object (with given by database id).
     */
    @Override
    public Transmission add(Transmission transmission) {
        return this.tx(session -> {
          session.save(transmission);
          return transmission;
        }).orElse(new Transmission());
    }

    /**
     * Updates the specified transmission.
     *
     * @param transmission the specified transmission object with updated params (except id).
     * @return updated transmission object.
     */
    @Override
    public Transmission update(Transmission transmission) {
        return this.tx(session -> {
            session.update(transmission);
            return transmission;
        }).orElse(new Transmission());
    }

    /**
     * Deletes the transmission with the specified id from the database.
     *
     * @param id the specified id.
     */
    @Override
    public void delete(int id) {
        this.tx(session -> {
           session.delete(new Transmission(id));
        });
    }

    /**
     * Finds the transmission by the specified id.
     *
     * @param id the specified id.
     * @return the transmission which is mapped to the specified id.
     */
    @Override
    public Transmission findById(int id) {
        return this.tx(session -> {
            return session.find(Transmission.class, id);
        }).orElse(new Transmission());
    }

    /**
     * Gets all transmission's list from database.
     *
     * @return the list of all transmissions.
     */
    @Override
    public List<Transmission> findAll() {
        return this.tx(session -> {
            final Query<Transmission> query = session.createQuery("from Transmission", Transmission.class);
            return query.list();
        }).orElse(new ArrayList<Transmission>());
    }

    /**
     * Deletes all transmissions from database.
     */
    @Override
    public void deleteAll() {
        this.tx(session -> {
            session.createQuery("delete Transmission").executeUpdate();
        });
    }

    /**
     * Finds a list of transmissions by name.
     *
     * @param name the specified transmission's name.
     * @return the list of transmissions with the specified name.
     */
    public List<Transmission> findTransmissionsByName(String name) {
        return this.tx(
                session -> {
                    final Query<Transmission> query = session.createQuery("from Transmission as transmission where transmission.name =: name", Transmission.class);
                    query.setParameter("name", name);
                    return query.list(); // name is not unique
                }).orElse(new ArrayList<Transmission>());
    }

    /**
     * Finds a transmission by its serial number.
     *
     * @param serialNumber the specified transmission's serial number.
     * @return the transmission with the specified serial number.
     */
    public Transmission findTransmissionBySerialNumber(String serialNumber) {
        return this.tx(
                session -> {
                    final Query<Transmission> query = session.createQuery("from Transmission as transmission where transmission.serialNumber =: serial_number", Transmission.class);
                    query.setParameter("serial_number", serialNumber);
                    return query.list().get(0); // s/n is unique
                }).orElse(new Transmission());
    }
}
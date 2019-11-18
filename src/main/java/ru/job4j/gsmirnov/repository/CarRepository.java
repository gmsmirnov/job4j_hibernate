package ru.job4j.gsmirnov.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ru.job4j.gsmirnov.models.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * The class which provides access to CRUD operations with {@link Car}-model through Hibernate API.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class CarRepository extends HibernateStore<Car> {
    /**
     * The logger.
     */
    private final static Logger LOG = LogManager.getLogger(CarRepository.class);

    /**
     * Puts the specified car object to the database.
     *
     * @param car the specified car object.
     * @return added car object (with given by database id).
     */
    @Override
    public Car add(Car car) {
        return this.tx(session -> {
           session.save(car);
           return car;
        }).orElse(new Car());
    }

    /**
     * Updates the specified car.
     *
     * @param car the specified car object with updated params (except id).
     * @return updated car object.
     */
    @Override
    public Car update(Car car) {
        return this.tx(session -> {
           session.update(car);
           return car;
        }).orElse(new Car());
    }

    /**
     * Deletes the car with the specified id from the database.
     *
     * @param id the specified id.
     */
    @Override
    public void delete(int id) {
        this.tx(session -> {
            session.delete(new Car(id));
        });
    }

    /**
     * Finds the car by the specified id.
     *
     * @param id the specified id.
     * @return the car which is mapped to the specified id.
     */
    @Override
    public Car findById(int id) {
        return this.tx(session -> {
            return session.find(Car.class, id);
        }).orElse(new Car());
    }

    /**
     * Gets all cars list from database.
     *
     * @return the list of all cars.
     */
    @Override
    public List<Car> findAll() {
        return this.tx(session -> {
            final Query<Car> query = session.createQuery("from Car", Car.class);
            return query.list();
        }).orElse(new ArrayList<Car>());
    }

    /**
     * Deletes all engines from database.
     */
    @Override
    public void deleteAll() {
        this.tx(session -> {
            session.createQuery("delete Car").executeUpdate();
        });
    }

    /**
     * Finds a list of cars by name.
     *
     * @param name the specified car's name.
     * @return the list of cars with the specified name.
     */
    public List<Car> findCarsByName(String name) {
        return this.tx(session -> {
                    final Query<Car> query = session.createQuery("from Car as car where car.name =: name", Car.class);
                    query.setParameter("name", name);
                    return query.list(); // name is not unique
                }
        ).orElse(new ArrayList<Car>());
    }
}
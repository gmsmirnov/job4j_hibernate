package ru.job4j.gsmirnov.models;

import java.util.Objects;

/**
 * The car-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class Car extends BaseEntity {
    /**
     * The car's name.
     */
    private String name;

    /**
     * Car's body.
     */
    private Body body;

    /**
     * Car's engine.
     */
    private Engine engine;

    /**
     * Car's transmission.
     */
    private Transmission transmission;

    /**
     * Default constructor.
     */
    public Car() {
    }

    public Car(int id) {
        super(id);
    }

    /**
     * The constructor with params.
     *
     * @param name         the specified name.
     * @param body         te specified body.
     * @param engine       the specified engine.
     * @param transmission the specified transmission.
     */
    public Car(String name, Body body, Engine engine, Transmission transmission) {
        this.name = name;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
    }

    /**
     * Gets the name of this car.
     *
     * @return the name of this engine.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the specified name to this car.
     *
     * @param name the specified name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets this car's body.
     *
     * @return this car's body.
     */
    public Body getBody() {
        return this.body;
    }

    /**
     * Sets the specified body to this car.
     *
     * @param body the specified body.
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Get this car's engine.
     *
     * @return this car's engine.
     */
    public Engine getEngine() {
        return this.engine;
    }

    /**
     * Sets the specified engine to this car.
     *
     * @param engine the specified engine.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Gets this car's transmission.
     *
     * @return this car's transmission.
     */
    public Transmission getTransmission() {
        return this.transmission;
    }

    /**
     * Sets the specified transmission to this car.
     *
     * @param transmission the specified transmission.
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * Checks if this {@link Car} object is equal to other {@link Car}-object.
     *
     * @param o other {@link Car} object
     * @return true if both cars are equals, false either.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Car car = (Car) o;
            result = Objects.equals(this.name, car.name)
                    && Objects.equals(this.body, car.body)
                    && Objects.equals(this.engine, car.engine)
                    && Objects.equals(this.transmission, car.transmission);
        }
        return result;
    }

    /**
     * Calculates hash-code for this {@link Car} object.
     *
     * @return calculated hash-code for this {@link Car} object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.body, this.engine, this.transmission);
    }

    /**
     * The string presentation of this {@link Car} object.
     *
     * @return the string presentation of this {@link Car} object.
     */
    @Override
    public String toString() {
        return String.format("Car{id='%d', name=%s, body=%s, engine=%s, transmission=%s}",
                super.getId(), this.name, this.body, this.engine, this.transmission);
    }
}
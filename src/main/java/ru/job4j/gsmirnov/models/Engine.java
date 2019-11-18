package ru.job4j.gsmirnov.models;

import java.util.Objects;

/**
 * The engine-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class Engine extends BaseEntity {
    /**
     * The engine's name.
     */
    private String name;

    /**
     * The engine's serial number.
     */
    private String serialNumber;

    /**
     * Default constructor.
     */
    public Engine() {
    }

    public Engine(int id) {
        super(id);
    }

    /**
     * The constructor with params.
     *
     * @param name the specified name.
     * @param serialNumber the specified serial number.
     */
    public Engine(String name, String serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the name of this engine.
     *
     * @return the name of this engine.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the specified name to this engine.
     *
     * @param name the specified name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the serial number of this engine.
     *
     * @return the serial number of this engine.
     */
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * Sets the specified serial number to this engine.
     *
     * @param serialNumber the specified serial number.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Checks this engine-model for equivalence with the specified engine-model.
     *
     * @param o the specified engine-model.
     * @return true if models are equals, false either.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Engine engine = (Engine) o;
            result = Objects.equals(this.name, engine.name) && Objects.equals(this.serialNumber, engine.serialNumber);
        }
        return result;
    }

    /**
     * Calculates hash code for this engine-model.
     *
     * @return calculated hash-code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.serialNumber);
    }

    /**
     * Presents this engine-model in a string view.
     *
     * @return the string presentation of this engine-model.
     */
    @Override
    public String toString() {
        return String.format("Engine{id = %d, name=%s, serialNumber=%s}", super.getId(), this.name, this.serialNumber);
    }
}
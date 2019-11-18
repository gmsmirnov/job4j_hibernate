package ru.job4j.gsmirnov.models;

import java.util.Objects;

/**
 * The body-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class Body extends BaseEntity {
    /**
     * The body's name.
     */
    private String name;

    /**
     * The body's serial number.
     */
    private String serialNumber;

    /**
     * Default constructor.
     */
    public Body() {
    }

    /**
     * Constructor with id.
     *
     * @param id the specified id.
     */
    public Body(int id) {
        super(id);
    }

    /**
     * The constructor with params.
     *
     * @param name the specified name.
     * @param serialNumber the specified serial number.
     */
    public Body(String name, String serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the name of this body.
     *
     * @return the name of this body.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the specified name to this body.
     *
     * @param name the specified name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the serial number of this body.
     *
     * @return the serial number of this body.
     */
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * Sets the specified serial number to this body.
     *
     * @param serialNumber the specified serial number.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Checks this body-model for equivalence with the specified body-model.
     *
     * @param o the specified body-model.
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
            Body body = (Body) o;
            result = Objects.equals(this.name, body.name) && Objects.equals(this.serialNumber, body.serialNumber);
        }
        return result;
    }

    /**
     * Calculates hash code for this body-model.
     *
     * @return calculated hash-code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.serialNumber);
    }

    /**
     * Presents this body-model in a string view.
     *
     * @return the string presentation of this body-model.
     */
    @Override
    public String toString() {
        return String.format("Body{id = %d, name=%s, serialNumber=%s}", super.getId(), this.name, this.serialNumber);
    }
}
package ru.job4j.gsmirnov.models;

import java.util.Objects;

/**
 * The transmission-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 23/09/2019
 */
public class Transmission extends BaseEntity {
    /**
     * The transmission's name.
     */
    private String name;

    /**
     * The transmission's serial number.
     */
    private String serialNumber;

    /**
     * Default constructor.
     */
    public Transmission() {
    }

    /**
     * Constructor with id.
     *
     * @param id the specified id.
     */
    public Transmission(int id) {
        super(id);
    }

    /**
     * The constructor with params.
     *
     * @param name the specified name.
     * @param serialNumber the specified serial number.
     */
    public Transmission(String name, String serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the name of this transmission.
     *
     * @return the name of this transmission.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the specified name to this transmission.
     *
     * @param name the specified name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the serial number of this transmission.
     *
     * @return the serial number of this transmission.
     */
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * Sets the specified serial number to this transmission.
     *
     * @param serialNumber the specified serial number.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Checks this transmission-model for equivalence with the specified transmission-model.
     *
     * @param o the specified transmission-model.
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
            Transmission transmission = (Transmission) o;
            result = Objects.equals(this.name, transmission.name) && Objects.equals(this.serialNumber, transmission.serialNumber);
        }
        return result;
    }

    /**
     * Calculates hash code for this transmission-model.
     *
     * @return calculated hash-code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.serialNumber);
    }

    /**
     * Presents this transmission-model in a string view.
     *
     * @return the string presentation of this transmission-model.
     */
    @Override
    public String toString() {
        return String.format("Transmission{id = %d, name=%s, serialNumber=%s}", super.getId(), this.name, this.serialNumber);
    }
}
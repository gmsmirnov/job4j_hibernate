package ru.job4j.gsmirnov.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * The transmission-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 23/09/2019
 */
@Entity
@Table (name = "transmission")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Transmission {
    /**
     * Transmission's id - unique.
     */
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;

    /**
     * The transmission's name.
     */
    @Column (name = "name")
    private String name;

    /**
     * The transmission's serial number.
     */
    @Column (name = "serial_number")
    private String serialNumber;

    /**
     * Default constructor.
     */
    public Transmission() {
        this.id = -1;
    }

    /**
     * Constructor with id.
     *
     * @param id the specified id.
     */
    public Transmission(int id) {
        this.id = id;
    }

    /**
     * The constructor with params.
     *
     * @param name the specified name.
     * @param serialNumber the specified serial number.
     */
    public Transmission(String name, String serialNumber) {
        this();
        this.name = name;
        this.serialNumber = serialNumber;
    }
}
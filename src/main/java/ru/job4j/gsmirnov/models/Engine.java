package ru.job4j.gsmirnov.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * The engine-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 23/09/2019
 */
@Entity
@Table (name = "engine")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Engine {
    /**
     * Engine's id - unique.
     */
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;

    /**
     * The engine's name.
     */
    @Column (name = "name")
    private String name;

    /**
     * The engine's serial number.
     */
    @Column (name = "serial_number")
    private String serialNumber;

    /**
     * Default constructor.
     */
    public Engine() {
        this.id = -1;
    }

    public Engine(int id) {
        this.id = id;
    }

    /**
     * The constructor with params.
     *
     * @param name the specified name.
     * @param serialNumber the specified serial number.
     */
    public Engine(String name, String serialNumber) {
        this();
        this.name = name;
        this.serialNumber = serialNumber;
    }
}
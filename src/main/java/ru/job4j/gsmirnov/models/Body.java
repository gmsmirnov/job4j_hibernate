package ru.job4j.gsmirnov.models;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The body-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 23/09/2019
 */
@Entity
@Table (name = "body")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Body {
    /**
     * Body's id - unique.
     */
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;

    /**
     * The body's name.
     */
    @Column (name = "name")
    private String name;

    /**
     * The body's serial number.
     */
    @Column (name = "serial_number")
    private String serialNumber;

    /**
     * Default constructor.
     */
    public Body() {
        this.id = -1;
    }

    /**
     * Constructor with id.
     *
     * @param id the specified id.
     */
    public Body(int id) {
        this.id = id;
    }

    /**
     * The constructor with params.
     *
     * @param name the specified name.
     * @param serialNumber the specified serial number.
     */
    public Body(String name, String serialNumber) {
        this();
        this.name = name;
        this.serialNumber = serialNumber;
    }
}
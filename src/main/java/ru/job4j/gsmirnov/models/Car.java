package ru.job4j.gsmirnov.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * The car-model's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 23/09/2019
 */
@Entity
@Table (name = "car")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Car {
    /**
     * Car's id - unique.
     */
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;

    /**
     * The car's name.
     */
    @Column (name = "name")
    private String name;

    /**
     * Car's body.
     */
    @ManyToOne (fetch = FetchType.EAGER, targetEntity = Body.class)
    @JoinColumn (name = "body_id")
    private Body body;

    /**
     * Car's engine.
     */
    @ManyToOne (fetch = FetchType.EAGER, targetEntity = Engine.class)
    @JoinColumn (name = "engine_id")
    private Engine engine;

    /**
     * Car's transmission.
     */
    @ManyToOne (fetch = FetchType.EAGER, targetEntity = Transmission.class)
    @JoinColumn (name = "transmission_id")
    private Transmission transmission;

    /**
     * Default constructor.
     */
    public Car() {
        this.id = -1;
    }

    public Car(int id) {
        this.id = id;
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
        this();
        this.name = name;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
    }
}
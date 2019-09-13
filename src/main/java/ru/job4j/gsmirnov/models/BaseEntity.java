package ru.job4j.gsmirnov.models;

/**
 * Base entity for models.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 09/09/2019
 */
public abstract class BaseEntity {
    /**
     * Entity's id - unique.
     */
    private int id;

    /**
     * Default constructor.
     */
    protected BaseEntity() {
        this.id = -1;
    }

    /**
     * Constructor with params.
     *
     * @param id the entity's id.
     */
    protected BaseEntity(int id) {
        this.id = id;
    }

    /**
     * Gets the entity's id.
     *
     * @return the entity's id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the entity's id.
     *
     * @param id the specified id.
     */
    public void setId(int id) {
        this.id = id;
    }
}

package ru.job4j.gsmirnov.models;

import java.util.Date;
import java.util.Objects;

/**
 * User's model description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.2
 * @since 06/06/2019
 */
public class User extends BaseEntity {
    /**
     * One year in milliseconds. Expired date (for account) is current time + one year.
     */
    public static final long YEAR = 1000L * 60 * 60 * 24 * 365;

    /**
     * User's name.
     */
    private String name;

    /**
     * Expired date - one year from creation or modification.
     */
    private Date expired;

    /**
     * The default constructor.
     */
    public User() {
        super();
        this.name = "";
        this.expired = new Date(System.currentTimeMillis() + User.YEAR);
    }

    /**
     * The constructor with params.
     *
     * @param id   the specified id.
     * @param name the specified name.
     */
    public User(int id, String name) {
        super(id);
        this.name = name;
        this.expired = new Date(System.currentTimeMillis() + User.YEAR);
    }

    /**
     * Gets the user's name.
     *
     * @return the user's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new name to this user.
     *
     * @param name - a new name to this user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the expired date for this user (one year from creation or modification.)
     *
     * @return the expired date.
     */
    public Date getExpired() {
        return this.expired;
    }

    /**
     * Sets new expired date for this user.
     *
     * @param expired - new expired date for this user.
     */
    public void setExpired(Date expired) {
        this.expired = expired;
    }

    /**
     * Compares this user with another user.
     *
     * @param o - another user.
     * @return true, if these users are equals, false either.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            User user = (User) o;
            result = Objects.equals(this.name, user.name);
        }
        return result;
    }

    /**
     * Calculates hash-code for this user.
     *
     * @return hash-code for this user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    /**
     * The string presentation of this user.
     *
     * @return the string presentation of this user.
     */
    @Override
    public String toString() {
        return String.format("User{id = %d, name=%s, expired=%s}", this.getId(), this.name, this.expired);
    }
}
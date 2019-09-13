package ru.job4j.gsmirnov.models;

import java.util.Date;
import java.util.Objects;

/**
 * Task's model description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 0.1
 * @since 02/09/2019
 */
public class Task extends BaseEntity {
    /**
     * Task's name - unique.
     */
    private String name;

    /**
     * Task's description.
     */
    private String desc;

    /**
     * Task's creation date.
     */
    private Date created;

    /**
     * Task's done flag.
     */
    private boolean done;

    /**
     * Default constructor.
     */
    public Task() {
        super();
        this.name = "";
        this.desc = "";
        this.created = new Date(System.currentTimeMillis());
        this.done = false;
    }

    /**
     * The constructor with the specified params.
     *
     * @param id      the specified id.
     * @param name    the specified name.
     * @param desc    the specified description.
     * @param created creation date.
     * @param done    the flag is task closed or not.
     */
    public Task(int id, String name, String desc, Date created, boolean done) {
        super(id);
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.done = done;
    }

    /**
     * The constructor with the specified params.
     *
     * @param name    the specified name.
     * @param desc    the specified description.
     * @param created creation date.
     * @param done    the flag is task closed or not.
     */
    public Task(String name, String desc, Date created, boolean done) {
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.done = done;
    }

    /**
     * Gets this task's name.
     *
     * @return this task's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets this task's name.
     *
     * @param name the specified name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets this task's description.
     *
     * @return this task's description.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Sets the task's description.
     *
     * @param desc the specified description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Task's creation time.
     *
     * @return this task's creation time.
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * Sets the task's creation date.
     *
     * @param created the specified creation date.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets this task's completion flag.
     *
     * @return this task's completion flag.
     */
    public boolean getDone() {
        return this.done;
    }

    /**
     * Sets the specified task's completion flag.
     *
     * @param done the specified task's completion flag.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Checks if this task with other task for equivalence.
     *
     * @param o the other task.
     * @return true if these tasks are equals, false either.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Task task = (Task) o;
            result = Objects.equals(this.name, task.name) && Objects.equals(this.desc, task.desc);
        }
        return result;
    }

    /**
     * Calculates the hash-code for this task.
     *
     * @return calculated hash-code for this task.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.desc);
    }

    /**
     * The string presentation of this task.
     *
     * @return the string presentation of this task.
     */
    @Override
    public String toString() {
        return String.format("Task{id=%s, name=%s, description=%s, created=%s, done=%s}",
                this.getId(), this.name, this.desc, this.created, this.done);
    }
}
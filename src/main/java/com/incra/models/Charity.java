package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;

/**
 * One charity in the system.
 *
 * @author Jeff Risberg
 * @since July 2014
 */
@Entity
@Table(name = "charities")
public class Charity extends AbstractDatedDatabaseItem {

    @Basic
    private String title;

    @ManyToOne
    private Charity parent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Charity getParent() {
        return parent;
    }

    public void setParent(Charity parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Charity)) return false;

        Charity rubric = (Charity) o;

        if (!title.equals(rubric.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Charity[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}
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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "ein")
    private String ein;

    @ManyToOne
    private Charity parent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
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

        Charity otherCharity = (Charity) o;

        if (!title.equals(otherCharity.title)) return false;

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
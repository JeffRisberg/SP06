package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Analytic definition
 *
 * @author Jeff Risberg
 * @since April 2014
 */
@Entity
@Table(name = "measure")
public class Measure extends AbstractDatedDatabaseItem {

    // type must go here somewhere

    @Basic
    private String title;

    // Constructor
    public Measure() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measure)) return false;

        Measure rubric = (Measure) o;

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

        sb.append("Measure[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}
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
@Table(name = "measures")
public class Measure extends AbstractDatedDatabaseItem {

    // type must go here somewhere

    @Basic
    private String name;

    // Constructor
    public Measure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measure)) return false;

        Measure measure = (Measure) o;

        if (!name.equals(measure.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Measure[name=");
        sb.append(name);
        sb.append("]");

        return sb.toString();
    }
}
package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Analytic definition
 *
 * @author Jeff Risberg
 * @since April 2014
 */
@Entity
@Table(name = "dimension")
public class Dimension extends AbstractDatedDatabaseItem {

    // type must go here somewhere

    @Basic
    private String title;

    // Constructor
    public Dimension() {
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
        if (!(o instanceof Dimension)) return false;

        Dimension rubric = (Dimension) o;

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

        sb.append("Dimension[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}
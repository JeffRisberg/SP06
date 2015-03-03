package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Vendor
 *
 * @author Jeff Risberg
 * @since August 2014
 */
@Entity
@Table(name = "vendors")
public class Vendor extends AbstractDatedDatabaseItem {

    @Basic
    private String title;

    // Constructor
    public Vendor() {
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
        if (!(o instanceof Vendor)) return false;

        Vendor otherVendor = (Vendor) o;

        if (!title.equals(otherVendor.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Box[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}
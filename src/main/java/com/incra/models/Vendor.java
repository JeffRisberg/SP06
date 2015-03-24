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
    private String name;

    // Constructor
    public Vendor() {
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
        if (!(o instanceof Vendor)) return false;

        Vendor otherVendor = (Vendor) o;

        if (!name.equals(otherVendor.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Vendor[name=");
        sb.append(name);
        sb.append("]");

        return sb.toString();
    }
}
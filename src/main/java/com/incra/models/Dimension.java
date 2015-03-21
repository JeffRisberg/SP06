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
@Table(name = "dimensions")
public class Dimension extends AbstractDatedDatabaseItem {

    // type must go here somewhere

    @Column(name = "name")
    private String name;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "field_name")
    private String fieldName;

    // Constructor
    public Dimension() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dimension)) return false;

        Dimension dimension = (Dimension) o;

        if (!name.equals(dimension.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Dimension[name=");
        sb.append(name);
        sb.append("]");

        return sb.toString();
    }
}
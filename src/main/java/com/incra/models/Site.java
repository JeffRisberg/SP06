package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.List;

/**
 * Stores site info
 */
@Entity
@Table(name = "site")
public class Site extends AbstractDatedDatabaseItem {

    @Basic
    private String name;

    @Column(name = "custom_css")
    private String customCSS;

    @OneToMany(mappedBy = "site")
    private List<Box> boxes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomCSS() {
        return customCSS;
    }

    public void setCustomCSS(String customCSS) {
        this.customCSS = customCSS;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;

        Site site = (Site) o;

        if (!name.equals(site.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
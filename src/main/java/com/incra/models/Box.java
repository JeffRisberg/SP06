package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Region on a page
 *
 * @author Jeff Risberg
 * @since February 2014
 */
@Entity
@Table(name = "box")
public class Box extends AbstractDatedDatabaseItem {

    // type must go here somewhere

    @Basic
    private String title;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @Basic
    @Column(name = "row_index")
    private Integer rowIndex;

    @Basic
    @Column(name = "col_index")
    private Integer colIndex;

    @Basic
    private Integer width;

    @Basic
    @Column(name = "image_file_name", nullable = true)
    private String imageFileName;

    @Transient
    private List<Rubric> rubrics = new ArrayList<Rubric>();

    // Constructor
    public Box() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public List<Rubric> getRubrics() {
        return rubrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;

        Box rubric = (Box) o;

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

        sb.append("Box[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}
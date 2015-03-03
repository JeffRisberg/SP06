package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;

/**
 * Piece of a page
 *
 * @author Jeff Risberg
 * @since February 2014
 */
@Entity
@Table(name = "rubric")
public class Rubric extends AbstractDatedDatabaseItem {

    @Basic
    private String title;

    @Basic
    @Column(name = "body", length = 10000)
    private String body;

    @Basic
    @Column(name = "seq_num")
    private Integer seqNum;

    @ManyToOne
    private Rubric parent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public Rubric getParent() {
        return parent;
    }

    public void setParent(Rubric parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rubric)) return false;

        Rubric rubric = (Rubric) o;

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

        sb.append("Rubric[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}
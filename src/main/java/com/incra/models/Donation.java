package com.incra.models;

import com.incra.database.AbstractDatabaseItem;
import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;

/**
 * A donation has a amouse
 *
 * @author Jeff Risberg
 * @since September 2014
 */
@Entity
@Table(name = "donations")
public class Donation extends AbstractDatedDatabaseItem {

    @ManyToOne
    @JoinColumn(name = "charity_id", nullable = false)
    protected Charity charity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    protected float amount;

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donation)) return false;

        Donation otherDonation = (Donation) o;

        if (!charity.equals(otherDonation.charity)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Donation[amount=");
        sb.append(getAmount());
        sb.append("]");

        return sb.toString();
    }
}
package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;

/**
 * A GiftCertificate has a date, a donor, a recipient email, and amount.
 *
 * @author Jeff Risberg
 * @since September 2014
 */
@Entity
@Table(name = "giftcertificates")
public class GiftCertificate extends AbstractDatedDatabaseItem {

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    protected User donor;

    @Column(name = "recipient_email")
    protected String recipientEmail;

    @Column(name = "amount")
    protected float amount;

    public User getDonor() {
        return donor;
    }

    public void setDonor(User donor) {
        this.donor = donor;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GiftCertificate)) return false;

        GiftCertificate otherGC = (GiftCertificate) o;

        if (!recipientEmail.equals(otherGC.recipientEmail)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + donor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("GiftCertificate[amount=");
        sb.append(getAmount());
        sb.append("]");

        return sb.toString();
    }
}
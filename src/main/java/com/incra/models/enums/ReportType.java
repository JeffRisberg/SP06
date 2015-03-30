package com.incra.models.enums;

/**
 * @author Jeff Risberg
 * @since 3/23/2015
 */
public enum ReportType {
    Donation("Donations"),
    GiftCertificate("Gift Certificates");

    protected String name;

    ReportType(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

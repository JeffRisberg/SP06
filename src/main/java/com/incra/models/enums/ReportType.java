package com.incra.models.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff Risberg
 * @since 3/23/2015
 */
public enum ReportType {
    Donation("Donations"),
    GiftCertificate("Gift Certificates"),
    Tracking("Tracking");

    protected String name;

    ReportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

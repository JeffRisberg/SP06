package com.incra.models.propertyEditor;

import com.incra.models.Donation;
import com.incra.models.Vendor;
import com.incra.services.DonationService;

import java.beans.PropertyEditorSupport;

public class DonationPropertyEditor extends PropertyEditorSupport {
    private final DonationService donationService;

    public DonationPropertyEditor(DonationService donationService) {
        this.donationService = donationService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(donationService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Donation) {
            return String.valueOf(((Donation) value).getId());
        } else {
            return super.getAsText();
        }
    }
}

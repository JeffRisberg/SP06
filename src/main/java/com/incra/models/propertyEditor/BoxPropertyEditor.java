package com.incra.models.propertyEditor;

import com.incra.models.Vendor;
import com.incra.services.DonationService;

import java.beans.PropertyEditorSupport;

public class BoxPropertyEditor extends PropertyEditorSupport {
    private final DonationService boxService;

    public BoxPropertyEditor(DonationService boxService) {
        this.boxService = boxService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(boxService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Vendor) {
            return String.valueOf(((Vendor) value).getId());
        } else {
            return super.getAsText();
        }
    }
}

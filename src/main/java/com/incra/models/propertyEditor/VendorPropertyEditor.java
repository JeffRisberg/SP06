package com.incra.models.propertyEditor;

import com.incra.models.Vendor;
import com.incra.services.VendorService;

import java.beans.PropertyEditorSupport;

public class VendorPropertyEditor extends PropertyEditorSupport {
    private final VendorService vendorService;

    public VendorPropertyEditor(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(vendorService.findEntityById(Integer.parseInt(text)));
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

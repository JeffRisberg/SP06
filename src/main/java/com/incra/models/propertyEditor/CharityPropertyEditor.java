package com.incra.models.propertyEditor;

import com.incra.models.Charity;
import com.incra.services.CharityService;

import java.beans.PropertyEditorSupport;

public class CharityPropertyEditor extends PropertyEditorSupport {
    private final CharityService charityService;

    public CharityPropertyEditor(CharityService charityService) {
        this.charityService = charityService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(charityService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Charity) {
            return String.valueOf(((Charity) value).getId());
        } else {
            return super.getAsText();
        }
    }
}

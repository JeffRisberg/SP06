package com.incra.models.propertyEditor;

import com.incra.models.Box;
import com.incra.services.BoxService;

import java.beans.PropertyEditorSupport;

public class BoxPropertyEditor extends PropertyEditorSupport {
    private final BoxService boxService;

    public BoxPropertyEditor(BoxService boxService) {
        this.boxService = boxService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(boxService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Box) {
            return String.valueOf(((Box) value).getId());
        } else {
            return super.getAsText();
        }
    }
}

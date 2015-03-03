package com.incra.models.propertyEditor;

import com.incra.models.Rubric;
import com.incra.services.RubricService;

import java.beans.PropertyEditorSupport;

public class RubricPropertyEditor extends PropertyEditorSupport {
    private final RubricService rubricService;

    public RubricPropertyEditor(RubricService rubricService) {
        this.rubricService = rubricService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(rubricService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Rubric) {
            return String.valueOf(((Rubric) value).getId());
        } else {
            return super.getAsText();
        }
    }
}

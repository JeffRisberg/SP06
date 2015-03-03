package com.incra.models.propertyEditor;

import com.incra.models.User;
import com.incra.services.UserService;

import java.beans.PropertyEditorSupport;

public class UserPropertyEditor extends PropertyEditorSupport {
    private final UserService userService;

    public UserPropertyEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(userService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof User) {
            return String.valueOf(((User) value).getId());
        } else {
            return super.getAsText();
        }
    }
}

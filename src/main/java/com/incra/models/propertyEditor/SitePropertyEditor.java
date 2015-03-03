package com.incra.models.propertyEditor;

import com.incra.models.Site;
import com.incra.services.SiteService;

import java.beans.PropertyEditorSupport;

public class SitePropertyEditor extends PropertyEditorSupport {
    private final SiteService siteService;

    public SitePropertyEditor(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(siteService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Site) {
            return String.valueOf(((Site) value).getId());
        } else {
            return super.getAsText();
        }
    }
}

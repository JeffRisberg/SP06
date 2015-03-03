package com.incra.controllers.dto;

/**
 * The <i>AdminPanel</i> dto...
 *
 * @author Jeffrey Risberg
 * @since 11/01/11
 */
public class AdminPanel {
    private String name;
    private String url;

    public AdminPanel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

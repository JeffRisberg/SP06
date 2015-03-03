package com.incra.pojo;

/**
 * The <i>MenuItem</i> class is a simple description of a item on a navigation
 * menu. Later programs change this into an entity so that menu structures can
 * be persisted.
 *
 * @author Jeffrey Risberg
 * @since 02/07/11
 */
public class MenuItem {
    protected String label;
    protected String url;
    protected boolean active;

    /**
     * Constructor
     */
    public MenuItem(String label, String url, boolean active) {
        this.label = label;
        this.url = url;
        this.active = active;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

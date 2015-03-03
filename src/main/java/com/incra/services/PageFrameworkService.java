package com.incra.services;

import com.incra.pojo.MenuItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The <i>PageFrameworkService</i> maintains a list of MenuItems, and allows one
 * to be selected as current based upon the request. The current item is
 * maintained in the session for the request.
 *
 * @author Jeffrey Risberg
 * @since 12/16/10
 */
@Transactional
@Repository
public class PageFrameworkService {
    private static final String CURRENT_MENU_ITEM = "PF_CURRENT_MENU_ITEM";
    private static final String FLASH_MESSAGE = "PF_FLASH_MESSAGE";
    private static final String IS_REDIRECT = "PF_IS_REDIRECT";

    private List<MenuItem> menuItems;

    /**
     * Constructor
     */
    public PageFrameworkService() {
        this.menuItems = new ArrayList<MenuItem>();

        this.menuItems.add(new MenuItem("Home", "/home", true));
        this.menuItems.add(new MenuItem("Products", "/product/list", true));
        this.menuItems.add(new MenuItem("Customers", "/customer/list", true));
        this.menuItems.add(new MenuItem("Sales Orders", "/salesOrder/list", true));
        this.menuItems.add(new MenuItem("Projects", "/project/list", true));
    }

    /**
     * Get the defined menu items. This doesn't vary by user session.
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Get the current menu item, which is stored in the user's session.
     */
    public MenuItem getCurrentMenuItem(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        return (MenuItem) session.getAttribute(CURRENT_MENU_ITEM);
    }

    /**
     * Change the current menu item based on the uri in the request.
     */
    public void setCurrentMenuItem(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String pathInfo = request.getContextPath();
        String uri = request.getRequestURI();
        String command = uri.substring(pathInfo.length());

        for (MenuItem menuItem : menuItems) {
            if (command.startsWith(menuItem.getUrl())) {
                session.setAttribute(CURRENT_MENU_ITEM, menuItem);
                break;
            }
        }
    }

    /**
     * Allow presentation layer to get the flash message.
     */
    public String getFlashMessage(HttpSession session) {
        return (String) session.getAttribute(FLASH_MESSAGE);
    }

    /**
     * Allow controller to set the flash message.
     */
    public void setFlashMessage(HttpSession session, String message) {
        session.setAttribute(FLASH_MESSAGE, message);
    }

    /**
     * Allow controller to indicate that this request is being redirected,
     * hence, the flash message should stay for another cycle.
     */
    public void setIsRedirect(HttpSession session, Boolean status) {
        session.setAttribute(IS_REDIRECT, status);
    }

    /**
     * Allow the PageFrameworkHandlerInterceptor to determine if this request
     * became a redirect, in which case the flash message should be retained.
     */
    public boolean getIsRedirect(HttpSession session) {
        return (Boolean) session.getAttribute(IS_REDIRECT);
    }
}

package com.incra.controllers;

import com.incra.controllers.dto.AdminPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * The <i>AdminHomeController</i> controller generates the home screen for all
 * admin functions, such as Sites, Boxes, and Users.
 *
 * @author Jeffrey Risberg
 * @since 11/15/11
 */
@Controller
public class AdminHomeController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AdminHomeController.class);

    public AdminHomeController() {
    }

    /**
     * Construct a description of the admin screen, out of the panels, then
     * render it. The code should be checking for permissions on the specific
     * panels, but it doesn't yet.
     */
    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/adminHome/**")
    public ModelAndView index() {

        List<AdminPanel> adminPanelList = new ArrayList<AdminPanel>();
        AdminPanel adminPanel;

        adminPanel = new AdminPanel("Sites", "/site");
        adminPanelList.add(adminPanel);

        adminPanel = new AdminPanel("Boxes", "/box");
        adminPanelList.add(adminPanel);

        adminPanel = new AdminPanel("Rubrics", "/rubric");
        adminPanelList.add(adminPanel);

        adminPanel = new AdminPanel("Users", "/user");
        adminPanelList.add(adminPanel);

        ModelAndView modelAndView = new ModelAndView("adminHome/index");
        modelAndView.addObject("adminPanelList", adminPanelList);

        return modelAndView;
    }
}
package com.incra.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The <i>AnalyticsController</i> controller shows the metadata associated with the charts.
 *
 * @author Jeffrey Risberg
 * @since August 2014
 */
@Controller
public class AnalyticsController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

    public AnalyticsController() {
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/analytics/**")
    public ModelAndView index() {


        ModelAndView modelAndView = new ModelAndView("analytics/index");


        return modelAndView;
    }
}
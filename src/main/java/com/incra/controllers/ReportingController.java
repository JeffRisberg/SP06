package com.incra.controllers;

import com.incra.controllers.dto.AdminPanel;
import com.incra.services.DimensionService;
import com.incra.services.MeasureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * The <i>ReportingController</i> controller generates charts.
 *
 * @author Jeffrey Risberg
 * @since August 2014
 */
@Controller
public class ReportingController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(ReportingController.class);

    @Autowired
    private DimensionService dimensionService;
    @Autowired
    private MeasureService measureService;

    public ReportingController() {
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/reporting/**")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("reporting/index");

        return modelAndView;
    }
}
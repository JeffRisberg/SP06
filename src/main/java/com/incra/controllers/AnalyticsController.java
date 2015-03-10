package com.incra.controllers;

import com.incra.models.Dimension;
import com.incra.models.Measure;
import com.incra.services.CharityService;
import com.incra.services.DimensionService;
import com.incra.services.MeasureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * The <i>AnalyticsController</i> controller shows the metadata associated with the charts.
 *
 * @author Jeffrey Risberg
 * @since August 2014
 */
@Controller
public class AnalyticsController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

    @Autowired
    private DimensionService dimensionService;
    @Autowired
    private MeasureService measureService;

    public AnalyticsController() {
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/analytics/**")
    public ModelAndView index() {

        List<Dimension> dimensions = dimensionService.findEntityList();
        List<Measure> measures = measureService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("analytics/index");
        modelAndView.addObject("dimensions", dimensions);
        modelAndView.addObject("measures", measures);

        return modelAndView;
    }
}
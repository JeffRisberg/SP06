package com.incra.controllers;

import com.incra.controllers.dto.AdminPanel;
import com.incra.controllers.dto.ReportData;
import com.incra.models.Dimension;
import com.incra.models.Measure;
import com.incra.models.enums.ReportType;
import com.incra.models.session.ReportingSession;
import com.incra.services.DimensionService;
import com.incra.services.MeasureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<Dimension> dimensions = dimensionService.findEntityList();
        List<Measure> measures = measureService.findEntityList();

        modelAndView.addObject("dimensions", dimensions);
        modelAndView.addObject("measures", measures);
        return modelAndView;
    }

    @RequestMapping(value = "/reporting/getData", headers="Accept=application/json")
    public
    @ResponseBody
    ReportData getData(HttpSession session) {
        ReportingSession reportingSession = getReportingSession(session);

        Date fromDate = new Date(115, 0, 1);
        Date toDate = new Date(115, 9, 13);
        List<Map> dataPoints = new ArrayList<Map>();

        ReportData reportData = new ReportData(fromDate, toDate, dataPoints);

        reportData.addPoint("Amex", 1234.56);
        reportData.addPoint("Cisco", 522.67);
        reportData.addPoint("JustGive", 478.12);

        return reportData;
    }

    /**
     * Support method
     */
    protected ReportingSession getReportingSession(HttpSession session) {
        ReportingSession reportingSession = (ReportingSession) session.getAttribute("ReportingSession");

        if (reportingSession == null) {
            List<Dimension> dimensions = dimensionService.findEntityList();
            List<Measure> measures = measureService.findEntityList();

            reportingSession = new ReportingSession(ReportType.Donation, dimensions.get(0), measures.get(0));
            session.setAttribute("ReportingSession", reportingSession);
        }

        return reportingSession;
    }
}
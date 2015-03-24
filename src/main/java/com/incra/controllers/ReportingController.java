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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
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
    @PersistenceContext
    private EntityManager em;

    public ReportingController() {
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/reporting/**")
    public ModelAndView index(HttpSession session) {
        ReportingSession reportingSession = getReportingSession(session);

        ModelAndView modelAndView = new ModelAndView("reporting/index");

        List<Dimension> dimensionList = dimensionService.findEntityList();
        List<Measure> measureList = measureService.findEntityList();

        modelAndView.addObject("dimensionList", dimensionList);
        modelAndView.addObject("measureList", measureList);
        modelAndView.addObject("command", reportingSession);

        return modelAndView;
    }

    @RequestMapping(value = "/reporting/getData", headers = "Accept=application/json")
    public
    @ResponseBody
    ReportData getData(HttpServletRequest request, HttpSession session) {
        ReportingSession reportingSession = getReportingSession(session);

        Integer dimensionId = 1;
        Dimension dimension = null;
        try {
            dimensionId = Integer.parseInt(request.getParameter("dimension"));
            dimension = dimensionService.findEntityById(dimensionId);
        } catch (Exception e) {
        }
        Integer measureId = 1;
        Measure measure = null;
        try {
            measureId = Integer.parseInt(request.getParameter("measure"));
            measure = measureService.findEntityById(measureId);
        } catch (Exception e) {
        }

        System.out.println(dimension);
        System.out.println(measure);

        Date fromDate = new Date(115, 0, 1);
        Date toDate = new Date(115, 9, 13);
        ReportData reportData = new ReportData(1, "Amount", fromDate, toDate);

        String usersSql = "select u.email, sum(d.amount) from donations d " +
                "left join users u on u.id = d.donor_id " +
                "group by u.id order by sum(d.amount) desc limit 5";
        String charitiesSql = "select u.email, sum(d.amount) from donations d " +
                "left join charities c on c.id = d.charity_id " +
                "group by c.id order by sum(d.amount) desc limit 5";

        Query query = em.createNativeQuery(dimensionId == 1 ? usersSql : charitiesSql);

        List<Object[]> rowList = query.getResultList();

        for (Object[] row : rowList) {
            String email = (String) row[0];
            Double amount = (Double) row[1];

            reportData.addPoint(email, amount);
        }

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
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
import java.math.BigInteger;
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
            reportingSession.setDimension(dimension);
        } catch (Exception e) {
        }
        Integer measureId = 1;
        Measure measure = null;
        try {
            measureId = Integer.parseInt(request.getParameter("measure"));
            measure = measureService.findEntityById(measureId);
            reportingSession.setMeasure(measure);
        } catch (Exception e) {
        }

        Date fromDate = new Date(115, 0, 1);
        Date toDate = new Date(115, 9, 13);
        String tableName = dimension.getTableName();
        String groupByField = dimension.getGroupField();
        String categoryField = dimension.getCategoryField();
        String name = measure.getName();
        String expression = measure.getExpression();

        ReportData reportData = new ReportData(1, name, fromDate, toDate);

        String sql = "select " + tableName + "." + categoryField + ", " + expression + " from donations ";

        String usersSql = "left join users on users.id = donations.donor_id ";

        String charitiesSql = "left join charities on charities.id = donations.charity_id ";

        String vendorsSql = "left join users on users.id = donations.donor_id " +
                "left join vendors on vendors.id = users.vendor_id ";

        if (dimensionId == 1) sql += usersSql;
        if (dimensionId == 2) sql += charitiesSql;
        if (dimensionId == 3) sql += vendorsSql;

        sql += "group by " + tableName + "." + groupByField + " order by " + expression + " desc limit 5";

        if (sql != null) {
            Query query = em.createNativeQuery(sql);
            List<Object[]> rowList = query.getResultList();

            for (Object[] row : rowList) {
                String category = (String) row[0];

                if (measure.getDatatype().equalsIgnoreCase("integer")) {
                    BigInteger count = (BigInteger) row[1];

                    reportData.addPoint(category, count.doubleValue());
                } else if (measure.getDatatype().equalsIgnoreCase("double")) {
                    Double amount = (Double) row[1];

                    reportData.addPoint(category, amount);
                } else if (measure.getDatatype().equalsIgnoreCase("float")) {
                    Float amount = (Float) row[1];

                    reportData.addPoint(category, amount.doubleValue());
                }
            }
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
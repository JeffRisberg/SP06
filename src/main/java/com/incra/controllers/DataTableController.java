package com.incra.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

/**
 * The <i>DataTableController</i> controller generates content for the DataTables plugin.
 *
 * @author Jeffrey Risberg
 * @since August 2014
 */
@Controller
public class DataTableController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(DataTableController.class);


    @PersistenceContext
    private EntityManager em;

    public DataTableController() {
    }

    @RequestMapping(value = "/dataTable/**")
    public ModelAndView index(HttpSession session) {
        //ReportingSession reportingSession = getReportingSession(session);

        ModelAndView modelAndView = new ModelAndView("dataTable/index");


        return modelAndView;
    }
}
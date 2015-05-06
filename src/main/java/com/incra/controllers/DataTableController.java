package com.incra.controllers;

import com.incra.controllers.dto.ReportData;
import com.incra.models.Dimension;
import com.incra.models.Measure;
import com.incra.models.Person;
import com.incra.models.enums.ReportType;
import com.incra.models.session.ReportingSession;
import com.incra.services.DimensionService;
import com.incra.services.MeasureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
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

    @RequestMapping(value = "/dataTable/getData", headers = "Accept=application/json")
    public
    @ResponseBody
    PersonJsonObject getData(HttpServletRequest request, HttpSession session) {

        //Fetch the page number from client
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;

        //Fetch search parameter
        String searchParameter = request.getParameter("sSearch");

        //Fetch Page display length
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

        //Create page list data
        List<Person> personsList = createPaginationData(pageDisplayLength);

        //Here is server side pagination logic. Based on the page number you could make call
        //to the data base create new list and send back to the client. For demo I am shuffling
        //the same list to show data randomly
        if (pageNumber == 1) {
            Collections.shuffle(personsList);
        } else if (pageNumber == 2) {
            Collections.shuffle(personsList);
        } else {
            Collections.shuffle(personsList);
        }

        //Search functionality: Returns filtered list based on search parameter
        personsList = getListBasedOnSearchParameter(searchParameter, personsList);

        PersonJsonObject personJsonObject = new PersonJsonObject();
        //Set Total display record
        personJsonObject.setiTotalDisplayRecords(500);
        //Set Total record
        personJsonObject.setiTotalRecords(500);
        personJsonObject.setAaData(personsList);

        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //String json2 = gson.toJson(personJsonObject);

        return personJsonObject;
    }

    private List<Person> getListBasedOnSearchParameter(String searchParameter, List<Person> personsList) {

        if (null != searchParameter && !searchParameter.equals("")) {
            List<Person> personsListForSearch = new ArrayList<Person>();
            searchParameter = searchParameter.toUpperCase();
            for (Person person : personsList) {
                if (person.getName().toUpperCase().indexOf(searchParameter) != -1 || person.getOffice().toUpperCase().indexOf(searchParameter) != -1
                        || person.getPhone().toUpperCase().indexOf(searchParameter) != -1 || person.getPosition().toUpperCase().indexOf(searchParameter) != -1
                        || person.getSalary().toUpperCase().indexOf(searchParameter) != -1 || person.getStart_date().toUpperCase().indexOf(searchParameter) != -1) {
                    personsListForSearch.add(person);
                }

            }
            personsList = personsListForSearch;
            personsListForSearch = null;
        }
        return personsList;
    }

    private List<Person> createPaginationData(Integer pageDisplayLength) {
        List<Person> personsList = new ArrayList<Person>();
        for (int i = 0; i < 1; i++) {
            Person person2 = new Person();
            person2.setName("John Landy");
            person2.setPosition("System Architect");
            person2.setSalary("$320,800");
            person2.setOffice("NY");
            person2.setPhone("999999999");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Igor Vornovitsky");
            person2.setPosition("Solution Architect");
            person2.setSalary("$340,800");
            person2.setOffice("NY");
            person2.setPhone("987897899");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Java Honk");
            person2.setPosition("Architect");
            person2.setSalary("$380,800");
            person2.setOffice("NY");
            person2.setPhone("1234567890");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Ramesh Arrepu");
            person2.setPosition("Sr. Architect");
            person2.setSalary("$310,800");
            person2.setOffice("NY");
            person2.setPhone("4654321234");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Bob Sidebottom");
            person2.setPosition("Architect");
            person2.setSalary("$300,800");
            person2.setOffice("NJ");
            person2.setPhone("9876543212");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

        }

        for (int i = 0; i < pageDisplayLength - 5; i++) {
            Person person2 = new Person();
            person2.setName("Zuke Torres");
            person2.setPosition("System Architect");
            person2.setSalary("$320,800");
            person2.setOffice("NY");
            person2.setPhone("999999999");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);
        }
        return personsList;
    }

    /** Used only in the above methods */
    public class PersonJsonObject {

        int iTotalRecords;
        int iTotalDisplayRecords;
        String sEcho;
        String sColumns;
        List<Person> aaData;

        public int getiTotalRecords() {
            return iTotalRecords;
        }

        public void setiTotalRecords(int iTotalRecords) {
            this.iTotalRecords = iTotalRecords;
        }

        public int getiTotalDisplayRecords() {
            return iTotalDisplayRecords;
        }

        public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
            this.iTotalDisplayRecords = iTotalDisplayRecords;
        }

        public String getsEcho() {
            return sEcho;
        }

        public void setsEcho(String sEcho) {
            this.sEcho = sEcho;
        }

        public String getsColumns() {
            return sColumns;
        }

        public void setsColumns(String sColumns) {
            this.sColumns = sColumns;
        }

        public List<Person> getAaData() {
            return aaData;
        }

        public void setAaData(List<Person> aaData) {
            this.aaData = aaData;
        }
    }
}
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

    protected static int dataSetSize = 1000;

    @PersistenceContext
    private EntityManager em;

    private List<Person> personsList;

    public DataTableController() {
        personsList = createPaginationData(dataSetSize);
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

        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart")) {
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        }

        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

        String searchParameter = request.getParameter("sSearch");

        List<Person> dataSet = personsList;
        if (searchParameter != null && !searchParameter.equals("")) {
            dataSet = getListBasedOnSearchParameter(searchParameter, personsList);
            System.out.println("Search Result is " + dataSet.size() + " records");
        }

        int fromIndex = ((pageNumber - 1) * pageDisplayLength);
        int toIndex = Math.min(dataSet.size() - 1, fromIndex + pageDisplayLength);
        System.out.println("From index " + fromIndex + " to index " + toIndex);
        List<Person> resultList = dataSet.subList(fromIndex, toIndex);
        System.out.println("Result List is " + resultList.size() + " records");

        PersonJsonObject personJsonObject = new PersonJsonObject();
        personJsonObject.setiTotalDisplayRecords(dataSet.size());
        personJsonObject.setiTotalRecords(dataSet.size());
        personJsonObject.setAaData(resultList);

        return personJsonObject;
    }

    private List<Person> getListBasedOnSearchParameter(String searchParameter, List<Person> personsList) {
        List<Person> result = new ArrayList<Person>();

        searchParameter = searchParameter.toUpperCase();

        for (Person person : personsList) {
            if (person.getName().toUpperCase().indexOf(searchParameter) != -1
                    || person.getOffice().toUpperCase().indexOf(searchParameter) != -1
                    || person.getPhone().toUpperCase().indexOf(searchParameter) != -1
                    || person.getPosition().toUpperCase().indexOf(searchParameter) != -1) {

                result.add(person);
            }
        }

        return result;
    }

    private List<Person> createPaginationData(int size) {

        String[] firstNames = {"Robert", "John", "Jeff", "Emily", "Brandon", "Lauren", "Tom", "Luke", "Igor", "Arnold",
                "Jane", "Helen", "Julia", "Roberta", "Larry", "Bill", "Sharon", "Peter", "Brian", "Vivek", "Aruna",
                "Indu", "James", "George", "Ivan", "Hollie", "Susan", "Amy", "Micheal", "Elliot", "Hope", "Nancy",
                "Richard", "Patrick", "Linda", "Yolanda", "Hank", "Steve", "Janet", "Roger", "Terry", "Barbara",
                "William", "Gini", "Thomas", "Percy"};
        String[] lastNames = {"Smith", "Jones", "Shepard", "Hill", "Lake", "Farmer", "Risberg", "Riopel", "Sandell",
                "Browning", "Stone", "Underhill", "Woods", "Warner", "Deleon", "Fitzpatrick", "Stewart", "Steadman",
                "Wagner", "Naples", "Palmer", "Glenn", "Carpenter", "Armstrong", "Grissom", "Bowles", "Clarke",
                "Lovell", "Woodfill", "Fields", "Barton", "Schofield", "Lindberg", "Hayes", "Byrne", "Anderson",
                "White", "Green", "Mitchel", "Christian", "Cooper", "Borman", "Mattingly"};
        String[] positions = {"Sr. Architect", "Architect", "Designer", "Data Entry", "Purchasing Agent", "System Architect", "CEO", "Developer", "Sales Rep", "Support Rep", "Product Manager", "Accountant", "CFO", "Financial Analyst"};
        String[] offices = {"New York", "London", "Dallas", "Tokyo", "San Francisco", "Chicago", "Boston", "Richmond", "Atlanta", "Memphis", "Denver", "Boulder"};
        String[] salaries = {"$120,800", "$170,400", "$140,500", "$135,000", "$140,200", "$150,000", "$180,900"};
        String[] startDates = {"05/05/2010", "06/03/1999", "02/05/2009", "12/12/2004", "03/14/2004", "09/26/2005", "04/15/2004"};

        List<Person> personsList = new ArrayList<Person>();
        for (int i = 0; i < size; i++) {
            String firstName = firstNames[(int) (Math.random() * firstNames.length)];
            String lastName = lastNames[(int) (Math.random() * lastNames.length)];
            String position = positions[(int) (Math.random() * positions.length)];
            String office = offices[(int) (Math.random() * offices.length)];
            String salary = salaries[(int) (Math.random() * salaries.length)];
            String startDate = startDates[(int) (Math.random() * startDates.length)];

            Person person = new Person();
            person.setName(firstName + " " + lastName);
            person.setPosition(position);
            person.setSalary(salary);
            person.setOffice(office);
            person.setPhone("800-555-1212");
            person.setStart_date(startDate);
            personsList.add(person);
        }
        return personsList;
    }

    /**
     * Used only in the above methods
     */
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
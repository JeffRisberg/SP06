package com.incra.controllers;

import com.incra.controllers.dto.AdminPanel;
import com.incra.models.Donation;
import com.incra.models.User;
import com.incra.services.DonationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The <i>HomeController</i> controller generates the home screen
 *
 * @author Jeffrey Risberg
 * @since August 2014
 */
@Controller
public class HomeController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private DonationService donationService;

    @RequestMapping("/")
    public ModelAndView root() {

        List<Donation> donationList = donationService.findEntityList();

        Collections.sort(donationList, new TimeSorter());

        ModelAndView modelAndView = new ModelAndView("home/index");
        modelAndView.addObject("donationList", donationList);

        return modelAndView;
    }

    public class TimeSorter implements Comparator<Donation> {

        public int compare(Donation don1, Donation don2) {
            if (don1.getDateCreated().before(don2.getDateCreated())) return -1;
            return 1;
        }
    }
}
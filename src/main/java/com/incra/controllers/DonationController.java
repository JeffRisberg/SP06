package com.incra.controllers;

import com.incra.models.Donation;
import com.incra.models.Vendor;
import com.incra.services.CharityService;
import com.incra.services.DonationService;
import com.incra.services.PageFrameworkService;
import com.incra.services.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The <i>BoxController</i> controller implements CRUD operations on Boxes.
 *
 * @author Jeffrey Risberg
 * @since 03/12/14
 */
@Controller
public class DonationController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(DonationController.class);

    @Autowired
    private DonationService donationService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private CharityService charityService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public DonationController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
    }

    @RequestMapping(value = "/donation/**")
    public String index() {
        return "redirect:/donation/list";
    }

    @RequestMapping(value = "/donation/list")
    public ModelAndView list(Object criteria) {

        List<Donation> donationList = donationService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("donation/list");
        modelAndView.addObject("donationList", donationList);
        return modelAndView;
    }

    @RequestMapping(value = "/donation/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Donation donation = donationService.findEntityById(id);
        if (donation != null) {
            model.addAttribute(donation);
            return "donation/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Box with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/donation/list";
        }
    }

    @RequestMapping(value = "/donation/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Donation donation = new Donation();

        ModelAndView modelAndView = new ModelAndView("donation/create");
        modelAndView.addObject("command", donation);
        return modelAndView;
    }

    @RequestMapping(value = "/donation/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {

        Donation donation = donationService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("donation/edit");
        modelAndView.addObject("command", donation);

        return modelAndView;
    }

    @RequestMapping(value = "/donation/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Donation donation,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "donation/edit";
        }

        try {
            if (donation.getDateCreated() == null) donation.setDateCreated(new Date());
            donation.setLastUpdated(new Date());

            donationService.save(donation);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/donation/list";
        }
        return "redirect:/donation/list";
    }

    @RequestMapping(value = "/donation/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Donation donation = donationService.findEntityById(id);
        if (donation != null) {
            try {
                donationService.delete(donation);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/donation/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Box with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/donation/list";
    }
}
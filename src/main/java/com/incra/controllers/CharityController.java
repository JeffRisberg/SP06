package com.incra.controllers;

import com.incra.models.Charity;
import com.incra.services.PageFrameworkService;
import com.incra.services.CharityService;
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
 * The <i>CharityController</i> controller implements CRUD operations on Charities.
 *
 * @author Jeffrey Risberg
 * @since 03/12/14
 */
@Controller
public class CharityController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(CharityController.class);

    @Autowired
    private CharityService charityService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public CharityController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
    }

    @RequestMapping(value = "/charity/**")
    public String index() {
        return "redirect:/charity/list";
    }

    @RequestMapping(value = "/charity/list")
    public ModelAndView list(Object criteria) {

        List<Charity> charityList = charityService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("charity/list");
        modelAndView.addObject("charityList", charityList);
        return modelAndView;
    }

    @RequestMapping(value = "/charity/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Charity charity = charityService.findEntityById(id);
        if (charity != null) {
            model.addAttribute(charity);
            return "charity/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Charity with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/charity/list";
        }
    }

    @RequestMapping(value = "/charity/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Charity charity = new Charity();

        ModelAndView modelAndView = new ModelAndView("charity/create");
        modelAndView.addObject("command", charity);
        return modelAndView;
    }

    @RequestMapping(value = "/charity/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id, String finalURL) {
        Charity charity = charityService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("charity/edit");
        modelAndView.addObject("command", charity);
        modelAndView.addObject("finalURL", finalURL);

        return modelAndView;
    }

    @RequestMapping(value = "/charity/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Charity charity, String finalURL,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "charity/edit";
        }

        try {
            if (charity.getDateCreated() == null) charity.setDateCreated(new Date());
            charity.setLastUpdated(new Date());

            charityService.save(charity);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/charity/list";
        }
        if (finalURL != null && finalURL.length() > 0) {
            return "redirect:" + finalURL;
        } else {
            return "redirect:/charity/list";
        }
    }

    @RequestMapping(value = "/charity/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Charity charity = charityService.findEntityById(id);
        if (charity != null) {
            try {
                charityService.delete(charity);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/charity/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Charity with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/charity/list";
    }
}
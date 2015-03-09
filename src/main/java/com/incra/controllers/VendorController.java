package com.incra.controllers;

import com.incra.models.Vendor;
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
 * The <i>VendorController</i> controller implements CRUD operations on Vendors.
 *
 * @author Jeffrey Risberg
 * @since 08/12/14
 */
@Controller
public class VendorController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    private VendorService vendorService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public VendorController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
    }

    @RequestMapping(value = "/vendor/**")
    public String index() {
        return "redirect:/vendor/list";
    }

    @RequestMapping(value = "/vendor/list")
    public ModelAndView list(Object criteria) {

        List<Vendor> vendorList = vendorService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("vendor/list");
        modelAndView.addObject("vendorList", vendorList);
        return modelAndView;
    }

    @RequestMapping(value = "/vendor/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Vendor vendor = vendorService.findEntityById(id);
        if (vendor != null) {
            model.addAttribute(vendor);
            return "vendor/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Vendor with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/vendor/list";
        }
    }

    @RequestMapping(value = "/vendor/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Vendor vendor = new Vendor();

        ModelAndView modelAndView = new ModelAndView("vendor/create");
        modelAndView.addObject("command", vendor);

        return modelAndView;
    }

    @RequestMapping(value = "/vendor/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        Vendor vendor = vendorService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("vendor/edit");
        modelAndView.addObject("command", vendor);

        return modelAndView;
    }

    @RequestMapping(value = "/vendor/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Vendor vendor,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "vendor/edit";
        }

        try {
            if (vendor.getDateCreated() == null) vendor.setDateCreated(getNow());
            vendor.setLastUpdated(getNow());

            vendorService.save(vendor);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/vendor/list";
        }
        return "redirect:/vendor/list";
    }

    @RequestMapping(value = "/vendor/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Vendor vendor = vendorService.findEntityById(id);
        if (vendor != null) {
            try {
                vendorService.delete(vendor);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/vendor/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Vendor with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/vendor/list";
    }
}
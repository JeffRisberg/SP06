package com.incra.controllers;

import com.incra.models.Box;
import com.incra.models.Site;
import com.incra.models.propertyEditor.SitePropertyEditor;
import com.incra.services.BoxService;
import com.incra.services.PageFrameworkService;
import com.incra.services.SiteService;
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
public class BoxController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(BoxController.class);

    @Autowired
    private BoxService boxService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public BoxController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
        dataBinder.registerCustomEditor(Site.class,
                new SitePropertyEditor(siteService));
    }

    @RequestMapping(value = "/box/**")
    public String index() {
        return "redirect:/box/list";
    }

    @RequestMapping(value = "/box/list")
    public ModelAndView list(Object criteria) {

        List<Box> boxList = boxService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("box/list");
        modelAndView.addObject("boxList", boxList);
        return modelAndView;
    }

    @RequestMapping(value = "/box/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Box box = boxService.findEntityById(id);
        if (box != null) {
            model.addAttribute(box);
            return "box/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Box with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/box/list";
        }
    }

    @RequestMapping(value = "/box/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Box box = new Box();
        List<Site> siteList = siteService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("box/create");
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("command", box);
        return modelAndView;
    }

    @RequestMapping(value = "/box/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        
        Box box = boxService.findEntityById(id);
        List<Site> siteList = siteService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("box/edit");
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("command", box);

        return modelAndView;
    }

    @RequestMapping(value = "/box/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Box box,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "box/edit";
        }

        try {
            if (box.getDateCreated() == null) box.setDateCreated(new Date());
            box.setLastUpdated(new Date());

            boxService.save(box);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/box/list";
        }
        return "redirect:/box/list";
    }

    @RequestMapping(value = "/box/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Box box = boxService.findEntityById(id);
        if (box != null) {
            try {
                boxService.delete(box);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/box/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Box with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/box/list";
    }
}
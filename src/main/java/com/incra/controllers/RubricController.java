package com.incra.controllers;

import com.incra.models.Rubric;
import com.incra.models.Site;
import com.incra.models.propertyEditor.SitePropertyEditor;
import com.incra.services.PageFrameworkService;
import com.incra.services.RubricService;
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
 * The <i>RubricController</i> controller implements CRUD operations on Rubrics.
 *
 * @author Jeffrey Risberg
 * @since 03/12/14
 */
@Controller
public class RubricController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(RubricController.class);

    @Autowired
    private RubricService rubricService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public RubricController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
    }

    @RequestMapping(value = "/rubric/**")
    public String index() {
        return "redirect:/rubric/list";
    }

    @RequestMapping(value = "/rubric/list")
    public ModelAndView list(Object criteria) {

        List<Rubric> rubricList = rubricService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("rubric/list");
        modelAndView.addObject("rubricList", rubricList);
        return modelAndView;
    }

    @RequestMapping(value = "/rubric/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Rubric rubric = rubricService.findEntityById(id);
        if (rubric != null) {
            model.addAttribute(rubric);
            return "rubric/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Rubric with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/rubric/list";
        }
    }

    @RequestMapping(value = "/rubric/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Rubric rubric = new Rubric();

        ModelAndView modelAndView = new ModelAndView("rubric/create");
        modelAndView.addObject("command", rubric);
        return modelAndView;
    }

    @RequestMapping(value = "/rubric/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id, String finalURL) {
        Rubric rubric = rubricService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("rubric/edit");
        modelAndView.addObject("command", rubric);
        modelAndView.addObject("finalURL", finalURL);

        return modelAndView;
    }

    @RequestMapping(value = "/rubric/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Rubric rubric, String finalURL,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "rubric/edit";
        }

        try {
            if (rubric.getDateCreated() == null) rubric.setDateCreated(new Date());
            rubric.setLastUpdated(new Date());

            rubricService.save(rubric);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/rubric/list";
        }
        if (finalURL != null && finalURL.length() > 0) {
            return "redirect:" + finalURL;
        } else {
            return "redirect:/rubric/list";
        }
    }

    @RequestMapping(value = "/rubric/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Rubric rubric = rubricService.findEntityById(id);
        if (rubric != null) {
            try {
                rubricService.delete(rubric);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/rubric/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Rubric with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/rubric/list";
    }
}
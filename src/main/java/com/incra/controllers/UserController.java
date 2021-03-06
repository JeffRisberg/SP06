package com.incra.controllers;

import com.incra.models.User;
import com.incra.models.Vendor;
import com.incra.models.propertyEditor.VendorPropertyEditor;
import com.incra.services.PageFrameworkService;
import com.incra.services.UserService;
import com.incra.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
        dataBinder.registerCustomEditor
                (Vendor.class, new VendorPropertyEditor(vendorService));
    }

    @RequestMapping(value = "/user/**")
    public String index() {
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> userList = userService.findActiveEntityList();

        model.addAttribute("user", new User());
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @RequestMapping(value = "/user/show/{userId}", method = RequestMethod.GET)
    public String showUser(@PathVariable("userId") int userId, Model model) {

        User user = userService.findEntityById(userId);

        if (user != null) {
            model.addAttribute(user);
            return "user/show";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView create() {

        User user = new User();

        ModelAndView modelAndView = new ModelAndView("user/create");
        modelAndView.addObject("command", user);

        return modelAndView;
    }

    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {

        User user = userService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("user/edit");
        modelAndView.addObject("command", user);

        return modelAndView;
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, BindingResult result) {

        userService.save(user);

        return "redirect:/user";
    }

    @RequestMapping(value = "/user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId, HttpSession session) {

        User user = userService.findEntityById(userId);

        if (user != null) {
            user.setDeleted(true);

            return "redirect:/user";
        } else {
            pageFrameworkService.setFlashMessage(session, "Could not find user with that id");
            return "redirect:/user";
        }
    }
}

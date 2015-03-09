package com.incra.controllers;

import com.incra.models.User;
import com.incra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findEntityList();

        model.addAttribute("user", new User());
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping(value = "/show/{userId}", method = RequestMethod.GET)
    public String showUser(@PathVariable("userId") int userId, Model model) {

        User user = userService.findEntityById(userId);

        if (user != null) {
            model.addAttribute(user);
            return "user/show";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {

        User user = userService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("user/edit");
        modelAndView.addObject("command", user);

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, BindingResult result) {

        userService.save(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {

        userService.delete(userService.findEntityById(userId));

        return "redirect:/";
    }
}

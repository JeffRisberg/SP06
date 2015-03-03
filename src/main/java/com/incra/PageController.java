package com.incra;

import com.incra.models.Site;
import com.incra.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String index(ModelMap model, int siteId) {

        Site site = siteService.findEntityById(siteId);

        //model.addAttribute("user", new User());
        //model.addAttribute("users", users);
        return "page/index";
    }

}

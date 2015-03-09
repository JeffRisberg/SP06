package com.incra;

import com.incra.models.Vendor;
import com.incra.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
    private VendorService vendorService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String index(ModelMap model, int vendorId) {

        Vendor vendor = vendorService.findEntityById(vendorId);

        //model.addAttribute("user", new User());
        //model.addAttribute("users", users);
        return "page/index";
    }

}

package com.incra.controllers;

import com.incra.models.Box;
import com.incra.models.Rubric;
import com.incra.models.Site;
import com.incra.services.BoxService;
import com.incra.services.RubricService;
import com.incra.services.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <i>PageController</i> controller
 *
 * @author Jeffrey Risberg
 * @since February 214
 */
@Controller
public class PageController {
    protected static Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private RubricService rubricService;

    public PageController() {
    }

    /**
     * Custom page
     */
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable int id, Model model, HttpSession session) {

        Site site = siteService.findEntityById(id);
        List<Box> boxes = boxService.findEntityListBySite(site);

        Map<Integer, List<Box>> rowMap = new HashMap<Integer, List<Box>>();
        int maxRowIndex = 0;

        for (Box box : boxes) {
            Integer rowIndex = box.getRowIndex();
            Integer colIndex = box.getColIndex();

            List<Rubric> rubrics = rubricService.findEntityListByBox(box);
            box.getRubrics().addAll(rubrics);

            List<Box> row = rowMap.get(rowIndex);
            if (row == null) {
                row = new ArrayList<Box>();
                rowMap.put(rowIndex, row);
                maxRowIndex = Math.max(maxRowIndex, rowIndex);
            }

            while (row.size() <= colIndex) row.add(null);
            row.set(colIndex, box);
        }

        List<List<Box>> rowList = new ArrayList<List<Box>>();
        for (int i = 0; i <= maxRowIndex; i++) {
            if (rowMap.get(i) != null) {
                rowList.add(rowMap.get(i));
            }
        }

        ModelAndView modelAndView = new ModelAndView("page/index");
        modelAndView.addObject("site", site);
        modelAndView.addObject("rowList", rowList);

        return modelAndView;
    }
}
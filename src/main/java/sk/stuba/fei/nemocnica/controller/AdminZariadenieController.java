package sk.stuba.fei.nemocnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.stuba.fei.nemocnica.domain.Zariadenie;
import sk.stuba.fei.nemocnica.service.ZariadenieService;

import java.util.Map;

/**
 * Created by pallo on 5/3/15.
 */
@Controller
@RequestMapping("/admin/zariadenie")
public class AdminZariadenieController {

    @Autowired
    private ZariadenieService zariadenieService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {

        model.put("pageTitle", "Admin Ambulancia");
        model.put("zariadenia", zariadenieService.findAll());

        return "admin/zariadenie/index";
    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {

        model.put("pageTitle", "Admin Zariadenia");
        model.put("zariadenie", new Zariadenie());

        return "admin/zariadenie/add";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Map<String, Object> model) {

        model.put("pageTitle", "Admin Zariadenia");
        model.put("zariadenie", zariadenieService.findOne(id));

        return "admin/zariadenie/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("zariadenie") Zariadenie zariadenie, Map<String, Object> model) {

        zariadenieService.save(zariadenie);

        return "redirect:/admin/zariadenie";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Map<String, Object> model) {

        zariadenieService.delete(id);

        return "redirect:/admin/zariadenie";
    }
}

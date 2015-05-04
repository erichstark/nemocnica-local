package sk.stuba.fei.nemocnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.stuba.fei.nemocnica.domain.Poistovna;
import sk.stuba.fei.nemocnica.service.PoistovnaService;

import java.util.Map;

/**
 * Created by pallo on 5/3/15.
 */
@Controller
@RequestMapping("/admin/poistovna")
public class AdminPoistovnaController {

    @Autowired
    private PoistovnaService poistovnaService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {

        model.put("pageTitle", "Admin Poistovne");
        model.put("poistovne", poistovnaService.findAll());

        return "admin/poistovna/index";
    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {

        model.put("pageTitle", "Admin Poistovne");
        model.put("poistovna", new Poistovna());

        return "admin/poistovna/add";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Map<String, Object> model) {

        model.put("pageTitle", "Admin Poistovne");
        model.put("poistovna", poistovnaService.findOne(id));

        return "admin/poistovna/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("poistovna") Poistovna poistovna, Map<String, Object> model) {

        poistovnaService.save(poistovna);

        return "redirect:/admin/poistovna";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Map<String, Object> model) {

        poistovnaService.delete(id);

        return "redirect:/admin/poistovna";
    }
}

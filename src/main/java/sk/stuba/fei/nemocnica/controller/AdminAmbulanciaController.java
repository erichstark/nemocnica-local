package sk.stuba.fei.nemocnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.nemocnica.domain.Ambulancia;
import sk.stuba.fei.nemocnica.domain.Zariadenie;
import sk.stuba.fei.nemocnica.formEntity.FormAmbulancia;
import sk.stuba.fei.nemocnica.service.AmbulanciaService;
import sk.stuba.fei.nemocnica.service.ZariadenieService;

import java.util.Map;

/**
 * Created by pallo on 5/2/15.
 */
@Controller
@RequestMapping("/admin/ambulancia")
public class AdminAmbulanciaController {

    @Autowired
    private AmbulanciaService ambulanciaService;
    @Autowired
    private ZariadenieService zariadenieService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {

        model.put("pageTitle", "Admin Ambulancia");
        model.put("ambulancie", ambulanciaService.findAll());

        return "admin/ambulancia/index";
    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {

        model.put("pageTitle", "Admin Ambulancia");
        model.put("ambulancia", new Ambulancia());
        model.put("zariadenia", zariadenieService.findAll());

        return "admin/ambulancia/add";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Map<String, Object> model) {

        model.put("pageTitle", "Admin Ambulancia");

        Ambulancia amb = ambulanciaService.findOne(id);

        model.put("ambulancia", amb);
        model.put("zariadenia", zariadenieService.findAll());

        return "admin/ambulancia/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("ambulancia") FormAmbulancia ambulancia, Map<String, Object> model) {

        Ambulancia newAmb = new Ambulancia();
        if (ambulanciaService.exists(ambulancia.getId())) {
            newAmb = ambulanciaService.findOne(ambulancia.getId());
        } else {
            newAmb.setId(ambulancia.getId());
        }
        newAmb.setName(ambulancia.getName());

        Zariadenie zariadenie = zariadenieService.findOne(ambulancia.getId_zariadenie());
        newAmb.setZariadenie(zariadenie);

        ambulanciaService.save(newAmb);

        //zariadenie.getAmbulancie().add(newAmb);
        //zariadenieService.save(zariadenie);

        return "redirect:/admin/ambulancia";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Map<String, Object> model) {

        ambulanciaService.delete(id);

        return "redirect:/admin/ambulancia";
    }
}

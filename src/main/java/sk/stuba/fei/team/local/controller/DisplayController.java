package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.stuba.fei.team.local.AlertMessage;
import sk.stuba.fei.team.local.domain.DisplayConfiguration;
import sk.stuba.fei.team.local.jms.CallInMessage;
import sk.stuba.fei.team.local.jms.JmsProducer;
import sk.stuba.fei.team.local.service.DisplayConfigurationService;
import sk.stuba.fei.team.local.service.OfficeService;

import java.util.HashSet;
import java.util.Map;

@Controller
@RequestMapping("/admin/display")
public class DisplayController {

    @Autowired
    JmsProducer jmsProducer;

    @Autowired
    DisplayConfigurationService displayConfigurationService;

    @Autowired
    OfficeService officeService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {
        model.put("displayList", displayConfigurationService.findAll());
        return "admin/display/index";
    }

    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    public String debug(Map<String, Object> model) {
        return "admin/display/debug";
    }

    @RequestMapping(value = "/debug", method = RequestMethod.POST)
    public String send(@ModelAttribute("callIn") CallInMessage callInMessage, Map<String, Object> model) {
        jmsProducer.sendMessage(callInMessage);
        model.put("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Sprava odoslana"));
        return "admin/display/debug";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editDisplay(@PathVariable String id, Map<String, Object> model) {
        DisplayConfiguration displayConfiguration = displayConfigurationService.findOne(id);
        model.put("display", displayConfiguration);
        model.put("displayOffices", displayConfiguration.getOffices());
        model.put("officeList", officeService.findAll());
        model.put("isNew", false);
        return "admin/display/edit";
    }

    @RequestMapping(value = "/new")
    public String newDisplay(Map<String, Object> model) {
        model.put("display", new DisplayConfiguration());
        model.put("displayOffices", new HashSet<>());
        model.put("officeList", officeService.findAll());
        model.put("isNew", true);
        return "admin/display/edit";
    }

    @RequestMapping(value = "/submit")
    public String submit(@ModelAttribute("display") DisplayConfiguration display, @ModelAttribute("submit") String submit, @ModelAttribute("isNew") Boolean isNew, Map<String, Object> model, RedirectAttributes redirectAttributes) {
        String action = submit.split("/")[0];
        switch (action) {
            case "save":
                if (isNew && displayConfigurationService.exists(display.getId())) {
                    model.put("alertMessage", new AlertMessage(AlertMessage.DANGER, "Zvolene ID je uz pouzite."));
                    break;
                }
                displayConfigurationService.save(display);
                redirectAttributes.addFlashAttribute("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Obrazovka uložená"));
                return "redirect:/admin/display";
            case "remove":
                break;
            case "add":
                break;
            default:
                model.put("alertMessage", new AlertMessage(AlertMessage.DANGER, "Neznama akcia."));
        }
        return "admin/display/edit";
    }

//    @RequestMapping(value = "/addOffice/{id}/{isNew}")
//    public String addOffice(@ModelAttribute("display") DisplayConfiguration display, @PathVariable Long id, @PathVariable Boolean isNew, Map<String, Object> model) {
//        display.getOffices().add(officeService.findOne(id));
//        model.put("display", display);
//        model.put("officeList", officeService.findAll());
//        model.put("isNew", isNew);
//        model.put("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Ambulancia pridaná."));
//        return "admin/display/edit";
//    }
//
//    @RequestMapping(value = "/removeOffice/{id}/{isNew}")
//    public String removeOffice(@ModelAttribute("display") DisplayConfiguration display, @PathVariable Long id, @PathVariable Boolean isNew, Map<String, Object> model) {
//        for (Office office : display.getOffices()) {
//            if (id.equals(office.getId())) {
//                display.getOffices().remove(office);
//                model.put("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Ambulancia odstránená."));
//            }
//        }
//        model.put("display", display);
//        model.put("officeList", officeService.findAll());
//        model.put("isNew", isNew);
//        return "admin/display/edit";
//    }
//
//    @RequestMapping(value = "/update")
//    public String update(@ModelAttribute("display") DisplayConfiguration displayConfiguration, Map<String, Object> model) {
//        displayConfigurationService.save(displayConfiguration);
//        model.put("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Zmeny uložené."));
//        return "redirect:/admin/display";
//    }
//
//    @RequestMapping(value = "/create")
//    public String create(@ModelAttribute("display") DisplayConfiguration displayConfiguration, Map<String, Object> model) {
//        if (displayConfigurationService.exists(displayConfiguration.getId())) {
//            model.put("display", displayConfiguration);
//            model.put("officeList", officeService.findAll());
//            model.put("isNew", true);
//            model.put("alertMessage", new AlertMessage(AlertMessage.DANGER, "Zvolené ID obrazovky je už použité."));
//            return "admin/display/edit";
//        } else {
//            displayConfigurationService.save(displayConfiguration);
//            model.put("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Obrazovka vytvorená."));
//            return "redirect:/admin/display";
//        }
//    }
//
//    @RequestMapping(value = "/delete/{id}")
//    public String save(@PathVariable String id, Map<String, Object> model) {
//        displayConfigurationService.delete(id);
//        model.put("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Obrazovka zmazaná."));
//        return "redirect:/admin/display";
//    }

}

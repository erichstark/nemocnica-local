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
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.jms.CallInMessage;
import sk.stuba.fei.team.local.jms.JmsProducer;
import sk.stuba.fei.team.local.service.DisplayConfigurationService;
import sk.stuba.fei.team.local.service.OfficeService;

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
    public String debug() {
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
        model.put("originalID", id);
        return "admin/display/edit";
    }

    @RequestMapping(value = "/new")
    public String newDisplay(Map<String, Object> model) {
        model.put("display", new DisplayConfiguration());
        model.put("originalID", "");
        return "admin/display/edit";
    }

    @RequestMapping(value = "/submit")
    public String submit(
            @ModelAttribute("display") DisplayConfiguration display,
            @ModelAttribute("submit") String submit,
            @ModelAttribute("originalID") String originalID,
            @ModelAttribute("searchTerm") String searchTerm,
            Map<String, Object> model,
            RedirectAttributes redirectAttributes) {
        String[] tokens = submit.split("/");
        String action = tokens[0];
        String actionId = tokens.length == 2 ? tokens[1] : "";

        //nasty hack to fix freemarker bug returning empty set with size of 1
        if (display.getOffices().size() == 1 && display.getOffices().iterator().next() == null) {
            display.getOffices().clear();
        }

        switch (action) {
            case "save":
                if (!originalID.equals(display.getId())) {
                    if (displayConfigurationService.exists(display.getId())) {
                        model.put("alertMessage", new AlertMessage(AlertMessage.DANGER, "Zvolene ID je uz pouzite."));
                        break;
                    } else {
                        if (!"".equals(originalID)) {
                            displayConfigurationService.delete(originalID);
                        }
                    }
                }
                displayConfigurationService.save(display);
                redirectAttributes.addFlashAttribute("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Obrazovka uložená"));
                return "redirect:/admin/display";
            case "remove":
                Long removeID = Long.parseLong(actionId);
                Office toRemove = null;
                for (Office office : display.getOffices()) {
                    if (office.getId().equals(removeID)) {
                        toRemove = office;
                    }
                }
                display.getOffices().remove(toRemove);
                break;
            case "add":
                Long addID = Long.parseLong(actionId);
                Office office = officeService.findOne(addID);
                if (display.getOffices().contains(office)) {
                    model.put("alertMessage", new AlertMessage(AlertMessage.WARNING, "Zvolená ambulancia sa už nachádza v zozname."));
                } else {
                    display.getOffices().add(office);
                }
                break;
            case "search":
                if (searchTerm.isEmpty()) {
                    model.put("alertMessage", new AlertMessage(AlertMessage.WARNING, "Vyhladavaný výraz nesmie byť prázdny."));
                } else {
                    model.put("searchResults", officeService.findByIdOrNameOrEmployeesName(searchTerm));
                }
                break;
            default:
                model.put("alertMessage", new AlertMessage(AlertMessage.DANGER, "Neznama akcia."));
        }
        return "admin/display/edit";
    }


    @RequestMapping(value = "/delete/{id}")
    public String save(@PathVariable String id, Map<String, Object> model) {
        displayConfigurationService.delete(id);
        model.put("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Obrazovka zmazaná."));
        return "redirect:/admin/display";
    }
}

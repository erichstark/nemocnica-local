package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.team.local.api.AlertMessage;
import sk.stuba.fei.team.local.domain.DisplayConfiguration;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Specialization;
import sk.stuba.fei.team.local.jms.CallInMessage;
import sk.stuba.fei.team.local.jms.JmsProducer;
import sk.stuba.fei.team.local.service.DisplayConfigurationService;
import sk.stuba.fei.team.local.service.OfficeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/search")
    public
    @ResponseBody
    List<SearchResult> search(@ModelAttribute("searchTerm") String searchTerm) {
        return officeService.findByNameOrEmployeeOrSpecialization(searchTerm).stream().map(SearchResult::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    AlertMessage save(@ModelAttribute("id") String id) {
        displayConfigurationService.delete(id);
        return new AlertMessage(AlertMessage.SUCCESS, "Obrazovka zmazaná.");
    }

    @RequestMapping(value = "/save")
    public
    @ResponseBody
    AlertMessage save(@RequestParam("id") String id, @RequestParam("originalID") String originalID, @RequestParam("offices") String offices) {
        if (id.isEmpty()) {
            return new AlertMessage(AlertMessage.DANGER, "ID nesmie byt prázdne");
        }
        if (displayConfigurationService.exists(id) && !originalID.equals(id)) {
            return new AlertMessage(AlertMessage.DANGER, "Zvolené ID je už použité.");
        }
        DisplayConfiguration displayConfiguration = new DisplayConfiguration();
        displayConfiguration.setId(id);
        for (String officeId : offices.split(",")) {
            displayConfiguration.getOffices().add(officeService.findOne(Long.parseLong(officeId)));
        }
        displayConfigurationService.save(displayConfiguration);
        return new AlertMessage(AlertMessage.SUCCESS, "Obrazovka uložená.");
    }


    private class SearchResult {
        private Long id;
        private String name;
        private String specializations;
        private String employees;

        public SearchResult(Office office) {
            id = office.getId();
            name = office.getName();
            specializations = office.getSpecializations().stream().map(Specialization::getName).collect(Collectors.joining(", "));
            employees = office.getEmployees().stream().map(e -> e.getPrefix_title() + " " + e.getFirstName() + " " + e.getLastName() + " " + e.getSuffix_title()).collect(Collectors.joining(", "));
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpecializations() {
            return specializations;
        }

        public void setSpecializations(String specializations) {
            this.specializations = specializations;
        }

        public String getEmployees() {
            return employees;
        }

        public void setEmployees(String employees) {
            this.employees = employees;
        }
    }
}

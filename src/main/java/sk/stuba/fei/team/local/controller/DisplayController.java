package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.stuba.fei.team.local.domain.DisplayConfiguration;
import sk.stuba.fei.team.local.jms.CallInMessage;
import sk.stuba.fei.team.local.jms.JmsProducer;
import sk.stuba.fei.team.local.service.DisplayConfigurationService;

import java.util.Map;

@Controller
@RequestMapping("/admin/display")
public class DisplayController {

    @Autowired
    JmsProducer jmsProducer;

    @Autowired
    DisplayConfigurationService displayConfigurationService;

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
    public String send(@ModelAttribute("callIn") CallInMessage callInMessage) {
        jmsProducer.sendMessage(callInMessage);
        return "redirect:debug";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable String id, Map<String, Object> model) {
        model.put("display", displayConfigurationService.findOne(id));
        return "admin/display/edit";
    }

    @RequestMapping(value = "/add")
    public String edit(Map<String, Object> model) {
        model.put("display", new DisplayConfiguration());
        return "admin/display/edit";
    }

    @RequestMapping(value = "/save")
    public String save(@ModelAttribute("display") DisplayConfiguration displayConfiguration) {
        return "redirect:/admin/display";
    }


}

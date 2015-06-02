package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.stuba.fei.team.local.jms.JmsProducer;

import java.util.Map;

@Controller
@RequestMapping("/admin/display")
public class DisplayController {

    @Autowired
    JmsProducer jmsProducer;

    @RequestMapping("")
    public String index(Map<String, Object> model) {
        return "admin/display/index";
    }

    @RequestMapping("/debug")
    public String debug(Map<String, Object> model) {
        return "admin/display/debug";
    }

}

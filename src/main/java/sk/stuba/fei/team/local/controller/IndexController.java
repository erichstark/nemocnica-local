package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.stuba.fei.team.local.JmsProducer;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    JmsProducer jmsProducer;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("pageTitle", "Hello Title");
        jmsProducer.sendMessage("MASAGE");
        return "index";
    }
}

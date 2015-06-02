package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.stuba.fei.team.local.jms.JmsProducer;

import java.util.*;

@Controller
@RequestMapping("/admin/rpiconfig")
public class RPiConfigController {

    @Autowired
    JmsProducer jmsProducer;

    @RequestMapping("")
    public String index(Map<String, Object> model) {
        model.put("pageTitle", "Administrácia obrazoviek");
        return "admin/rpiconfig/index";
    }

}

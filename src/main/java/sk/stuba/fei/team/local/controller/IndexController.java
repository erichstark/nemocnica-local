package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.repository.InsuranceRepository;
import sk.stuba.fei.team.local.service.EmployeeService;

import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    RestConsumer restConsumer;

    @Autowired
    InsuranceRepository insuranceRepository;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }
}

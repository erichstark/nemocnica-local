package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Insurance;
import sk.stuba.fei.team.local.repository.InsuranceRepository;
import sk.stuba.fei.team.local.service.EmployeeService;

import java.util.Date;
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
        Employee test = employeeService.findByUsername("test");
        Insurance[] insurance = (Insurance[]) restConsumer.post("insurance/update", new Date(0), Insurance[].class);
        for (Insurance in : insurance) {
            insuranceRepository.save(in);
        }

        return "index";
    }
}

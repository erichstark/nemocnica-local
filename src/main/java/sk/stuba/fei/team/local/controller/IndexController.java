package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.repository.InsuranceRepository;
import sk.stuba.fei.team.local.security.CustomUser;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        CustomUser userDetails = (CustomUser) principal;
        Employee employee = employeeService.findOne(userDetails.getUsername());

        if (employee == null)
            return "admin";

        if (employee.getStringAuthorities().contains("USER")) {
            if (employee.getOffices().size() == 1) {
                Long officeId = employee.getOffices().iterator().next().getId();
                return "office/" + officeId;
            }
        } else {
            return "admin";
        }
        return "index";
    }
}

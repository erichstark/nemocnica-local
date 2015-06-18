package sk.stuba.fei.team.local.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.team.local.api.AlertMessage;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Facility;
import sk.stuba.fei.team.local.repository.EmployeeRepository;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;
import sk.stuba.fei.team.local.service.EmployeeService;
import sk.stuba.fei.team.local.service.FacilityService;
import sk.stuba.fei.team.local.service.InsuranceService;
import sk.stuba.fei.team.local.service.SpecializationService;

import java.util.Collections;

@Controller
@RequestMapping("/setup")
public class SetupController {
    private Logger logger = LoggerFactory.getLogger(SetupController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private RestConsumer restConsumer;

    @RequestMapping(value = "")
    public String index() {
        return "/admin/facility/setup";
    }

    @RequestMapping(value = "/facility", method = RequestMethod.POST)
    public
    @ResponseBody
    AlertMessage facility(@ModelAttribute("facility") Facility facility) {
        try {
            restConsumer.configure(facility);

            facility.setEnabled(false);
            if (!facilityService.save(facility)) {
                return new AlertMessage(AlertMessage.DANGER, "Zariadenie so zvoleným názvom už existuje.");
            }

            insuranceService.update();
            specializationService.update();
        } catch (Exception e) {
            logger.error("Failed to setup system.", e);
            return new AlertMessage(AlertMessage.DANGER, "Zlyhala komunikácia s globálným serverom. Prosím, skontrolujte parametre synchronizácie.");
        }
        return new AlertMessage(AlertMessage.SUCCESS, "");
    }

    @RequestMapping(value = "/admin")
    public
    @ResponseBody
    AlertMessage admin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (employeeService.exists(username)) {
            return new AlertMessage(AlertMessage.DANGER, "Zvolené používateľské meno je už obsadené.");
        }
        employeeService.save(new Employee(username, new PBKDF2WithHmacSHA1().encode(password), Collections.singleton(new SimpleGrantedAuthority("ADMIN"))));
        Facility facility = facilityService.getFacility();
        facility.setEnabled(true);
        facilityService.save(facility);
        employeeRepository.delete("admin");
        return new AlertMessage(AlertMessage.SUCCESS, "");
    }
}

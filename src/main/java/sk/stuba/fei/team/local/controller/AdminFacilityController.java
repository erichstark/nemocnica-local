package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Facility;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;
import sk.stuba.fei.team.local.service.EmployeeService;
import sk.stuba.fei.team.local.service.FacilityService;
import sk.stuba.fei.team.local.service.InsuranceService;
import sk.stuba.fei.team.local.service.SpecializationService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminFacilityController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FacilityService facilityService;

    @Autowired
    SpecializationService specializationService;

    @Autowired
    InsuranceService insuranceService;

    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public String setup() {
        return "/admin/facility/setup";
    }

    @RequestMapping(value = "/setup/facility", method = RequestMethod.POST)
    private
    @ResponseBody
    String save(@ModelAttribute("facility") Facility facility) {
        try {
            facilityService.save(facility);
        } catch (Exception e) {
            return "Zlyhala komunikacia s globálnym serverom. Skontrolujte nastavenia.";
        }
        return "true";
    }

    private void createAdminAccount(ConfigurableApplicationContext context) {
        PasswordEncoder encoder = new PBKDF2WithHmacSHA1();
        if (employeeService.findOne("admin") == null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            Employee userDetails = new Employee("admin", encoder.encode("admin123"), authorities);
            employeeService.save(userDetails);
        }
    }
}

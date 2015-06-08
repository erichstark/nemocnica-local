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
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Facility;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;
import sk.stuba.fei.team.local.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String setup() {
        return "/setup/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    private String save(@ModelAttribute("facility") Facility facility, Map<String, Object> model) {
        model.put("initialization", true);
        return "/setup/index";
    }

    private void createAdmin(ConfigurableApplicationContext context) {
        PasswordEncoder encoder = new PBKDF2WithHmacSHA1();
        if (employeeService.findByUsername("admin") == null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            Employee userDetails = new Employee("admin", encoder.encode("admin123"), authorities);
            employeeService.save(userDetails);
        }
    }
}

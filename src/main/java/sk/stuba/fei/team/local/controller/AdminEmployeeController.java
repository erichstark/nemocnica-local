package sk.stuba.fei.team.local.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.team.local.api.AlertMessage;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Specialization;
import sk.stuba.fei.team.local.domain.ajax.JsonEmployee;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;
import sk.stuba.fei.team.local.service.EmployeeService;
import sk.stuba.fei.team.local.service.SpecializationService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/employee")
public class AdminEmployeeController {

    final static Logger logger = LoggerFactory.getLogger(AdminEmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SpecializationService specializationService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {
        employeeService.update();
        model.put("employees", employeeService.findAll());
        return "admin/employee/index";
    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {
        model.put("employee", new Employee());
        return "admin/employee/edit";
    }

    @RequestMapping(value = "/specialization/search")
    public
    @ResponseBody
    List<SearchResult> search(@ModelAttribute("searchTerm") String searchTerm) {
        return specializationService.findByName(searchTerm).stream().map(SearchResult::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/save", consumes = "application/json")
    public
    @ResponseBody
    AlertMessage save(@RequestBody JsonEmployee data) {

        Employee employee;
        PasswordEncoder encoder = new PBKDF2WithHmacSHA1();

        if (!employeeService.exists(data.getEmployee().getUsername())) {
            employee = data.getEmployee();

        } else {
            employee = employeeService.findOne(data.getEmployee().getUsername());
            employee.setAccountNonLocked(data.getEmployee().isAccountNonLocked());
            employee.setAccountNonExpired(data.getEmployee().isAccountNonExpired());
            employee.setCredentialsNonExpired(data.getEmployee().isCredentialsNonExpired());
            employee.setEnabled(data.getEmployee().isEnabled());
            employee.setFirstName(data.getEmployee().getFirstName());
            employee.setLastName(data.getEmployee().getLastName());
            employee.setPrefix_title(data.getEmployee().getPrefix_title());
            employee.setSuffix_title(data.getEmployee().getSuffix_title());
        }

        if (data.getEmployee().getPassword() != null)
            employee.setPassword(encoder.encode(data.getEmployee().getPassword()));

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(data.getAutority()));
        employee.setAuthorities(authorities);

        employee.getSpecializations().clear();
        for (String specId : data.getSpecializations().split(",")) {
            if(!specId.isEmpty())
                employee.getSpecializations().add(specializationService.findOne(Long.parseLong(specId)));
        }
        employeeService.save(employee);
        return new AlertMessage(AlertMessage.SUCCESS, "Zamestnanec uložený.");
    }

    @RequestMapping(value = "/edit/{username}")
    public String edit(@PathVariable String username, Map<String, Object> model) {
        Employee employee = employeeService.findOne(username);
        model.put("employee", employee);
        return "admin/employee/edit";
    }

    private class SearchResult {
        private Long id;
        private String name;

        public SearchResult(Specialization specialization) {
            id = specialization.getId();
            name = specialization.getName();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

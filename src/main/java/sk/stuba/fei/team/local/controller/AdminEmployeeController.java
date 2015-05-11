package sk.stuba.fei.team.local.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.service.EmployeeService;
import sk.stuba.fei.team.local.service.OfficeService;
import sk.stuba.fei.team.local.service.SpecializationService;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by pallo on 5/11/15.
 */
@Controller
@RequestMapping("/admin/employee")
public class AdminEmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OfficeService officeService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {

        model.put("pageTitle", "Admin Zamestnanec");
        model.put("employees", employeeService.findAll());

        return "admin/employee/index";
    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {

        model.put("pageTitle", "Admin Zamestnanec");
        model.put("employee", new Employee());
        model.put("offices", officeService.findAll());

        return "admin/employee/add";
    }

    @RequestMapping(value = "/edit/{username}")
    public String edit(@PathVariable String username, Map<String, Object> model) {

        Employee employee = employeeService.findOne(username);
        model.put("pageTitle", "Admin Zamestnanec");
        model.put("employee", employee);
        model.put("offices", officeService.findAll());

        return "admin/employee/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("employee") Employee employee, @RequestParam String autority) {

        PasswordEncoder encoder = new PBKDF2WithHmacSHA1();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(autority));
        employee.setPassword(encoder.encode(employee.getPassword()));
        employee.setAuthorities(authorities);

        employeeService.save(employee);

        return "redirect:/admin/employee";
    }

    @RequestMapping(value = "/delete/{username}")
    public String delete(@PathVariable String username) {

        employeeService.delete(username);

        return "redirect:/admin/employee";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("text") String text, Map<String, Object> model) {

        Iterable<Employee> employees = employeeService.findPatientByUsernameOrFirstOrSurname(text);

        model.put("pageTitle", "Admin Zamestnanec");
        model.put("search", text);
        model.put("employees", employees);

        return "admin/employee/index";
    }

    @RequestMapping(value = "/clear")
    public String clear(Map<String, Object> model) {
        model.put("pageTitle", "Admin Zamestnanec");
        model.put("employees", employeeService.findAll());

        return "admin/employee/index";
    }

    @RequestMapping(value = "/office/add", method = RequestMethod.POST)
    public String officeAdd(@RequestParam("username") String username, @RequestParam("id_office") Long id_office) {

        Office office = officeService.findOne(id_office);
        Employee employee = employeeService.findOne(username);

        employee.getOffices().add(office);
        office.getEmployees().add(employee);

        employeeService.save(employee);

        return "redirect:/admin/employee/edit/" + username;
    }

    @RequestMapping(value = "{username}/office/{id_office}/delete")
    public String officeDelete(@PathVariable("username") String username, @PathVariable("id_office") Long id_office) {

        Employee employee = employeeService.findOne(username);
        for (Office o : employee.getOffices()) {
            if (o.getId() == id_office) {
                employee.getOffices().remove(o);
            }
        }

        employeeService.save(employee);

        return "redirect:/admin/employee/edit/" + username;
    }

    @RequestMapping(value = "/specialization/add", method = RequestMethod.POST)
    public String specializationAdd(@RequestParam("username") String username, @RequestParam("specialization") String specialization) {

        Employee employee = employeeService.findOne(username);
        employee.getSpecializations().add(specialization);

        employeeService.save(employee);

        return "redirect:/admin/employee/edit/" + username;
    }

    @RequestMapping(value = "{username}/specialization/{specialization}/delete")
    public String specializationDelete(@PathVariable("username") String username, @PathVariable("specialization") int specialization) {

        Employee employee = employeeService.findOne(username);
        employee.getSpecializations().remove(specialization);

        employeeService.save(employee);

        return "redirect:/admin/employee/edit/" + username;
    }
}

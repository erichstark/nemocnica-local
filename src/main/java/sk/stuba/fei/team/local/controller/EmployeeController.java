package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.stuba.fei.team.local.api.AlertMessage;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Specialization;
import sk.stuba.fei.team.local.security.CustomUser;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;
import sk.stuba.fei.team.local.service.EmployeeService;
import sk.stuba.fei.team.local.service.SpecializationService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SpecializationService specializationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String profile(Map<String, Object> model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        CustomUser userDetails = (CustomUser) principal;
        Employee e = employeeService.findOne(userDetails.getUsername());

        FormEmployee formEmployee = new FormEmployee(e.getFirstName(), e.getLastName(), e.getPrefix_title(), e.getSuffix_title());

        model.put("employeer", formEmployee);
        model.put("specializations", e.getSpecializations());

        return "/employee/profile";
    }

    @RequestMapping(value = "/save")
    public
    @ResponseBody
    AlertMessage save(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("prefix_title") String prefix_title,
                      @RequestParam("suffix_title") String suffix_title, @RequestParam("speclist") String speclist) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        CustomUser userDetails = (CustomUser) principal;
        Employee employee = employeeService.findOne(userDetails.getUsername());

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPrefix_title(prefix_title);
        employee.setSuffix_title(suffix_title);

        employee.getSpecializations().clear();
        for (String specId : speclist.split(",")) {
            if (!specId.isEmpty())
                employee.getSpecializations().add(specializationService.findOne(Long.parseLong(specId)));
        }
        employeeService.save(employee);
        return new AlertMessage(AlertMessage.SUCCESS, "Zamestnanec uložený.");
    }

    @RequestMapping(value = "/specialization/search")
    public
    @ResponseBody
    List<SearchResult> search(@ModelAttribute("searchTerm") String searchTerm) {
        return specializationService.findByName(searchTerm).stream().map(SearchResult::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "password")
    public String changePassword() {
        return "/employee/password";
    }

    @RequestMapping(value = "password/save", method = RequestMethod.POST)
    public String savePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("password") String password,
                               @RequestParam("passwordCheck") String passwordCheck, RedirectAttributes redirectAttributes) {

        if (oldPassword.isEmpty() || password.isEmpty() || passwordCheck.isEmpty()) {
            redirectAttributes.addFlashAttribute("alertMessage", new AlertMessage(AlertMessage.DANGER, "Zadali ste prázdne heslo."));
            return "redirect:/employee/password";
        }
        if (!password.equals(passwordCheck)) {
            redirectAttributes.addFlashAttribute("alertMessage", new AlertMessage(AlertMessage.DANGER, "Nezadali ste rovnaké heslá."));
            return "redirect:/employee/password";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        CustomUser userDetails = (CustomUser) principal;
        Employee employee = employeeService.findOne(userDetails.getUsername());
        PasswordEncoder encoder = new PBKDF2WithHmacSHA1();

        if (!encoder.matches(oldPassword, employee.getPassword())) {
            redirectAttributes.addFlashAttribute("alertMessage", new AlertMessage(AlertMessage.DANGER, "Zadali ste zlé pôvodné heslo."));
            return "redirect:/employee/password";
        }

        employee.setPassword(encoder.encode(password));
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("alertMessage", new AlertMessage(AlertMessage.SUCCESS, "Heslo zmenené."));
        return "redirect:/employee/password";
    }

    private class FormEmployee {
        private String firstName;
        private String lastName;
        private String prefix_title;
        private String suffix_title;
        private String speclist;

        public FormEmployee(String firstName, String lastName, String prefix_title, String suffix_title) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.prefix_title = prefix_title;
            this.suffix_title = suffix_title;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPrefix_title() {
            return prefix_title;
        }

        public void setPrefix_title(String prefix_title) {
            this.prefix_title = prefix_title;
        }

        public String getSuffix_title() {
            return suffix_title;
        }

        public void setSuffix_title(String suffix_title) {
            this.suffix_title = suffix_title;
        }

        public String getSpeclist() {
            return speclist;
        }

        public void setSpeclist(String speclist) {
            this.speclist = speclist;
        }
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

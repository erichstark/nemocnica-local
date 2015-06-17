package sk.stuba.fei.team.local.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.stuba.fei.team.local.api.AlertMessage;
import sk.stuba.fei.team.local.domain.*;
import sk.stuba.fei.team.local.service.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/office")
public class AdminOfficeController {

    final static Logger logger = LoggerFactory.getLogger(AdminOfficeController.class);

    @Autowired
    private OfficeService officeService;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OpeningHoursService openingHoursService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {
        model.put("offices", officeService.findAll());
        return "admin/office/index";
    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {
        Office office = new Office();
        office.setEnabled(true);
        model.put("office", office);
        return "admin/office/edit";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Map<String, Object> model) {
        Office office = officeService.findOne(id);
        model.put("office", office);
        return "admin/office/edit";
    }

    @RequestMapping(value = "/save")
    public
    @ResponseBody
    AlertMessage save(@RequestParam("id") Long id, @RequestParam("enabled") boolean enabled, @RequestParam("name") String name) {

        Office office;
        if (id == null) {
            office = new Office();
            office.setFacility(facilityService.getFacility());
        } else {
            office = officeService.findOne(id);
        }

        office.setEnabled(enabled);
        office.setName(name);

        officeService.save(office);
        return new AlertMessage(AlertMessage.SUCCESS, "Ambulancia uložená.", office.getId());
    }

    @RequestMapping(value = "/hours/{id}")
    public String hours(@PathVariable Long id, Map<String, Object> model) {
        Office office = officeService.findOne(id);
        if (office.getHours().isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                OpeningHours openingHours = new OpeningHours(i);
                openingHours.setOffice(office);
                openingHoursService.save(openingHours);
            }
        }
        model.put("office", office);
        return "admin/office/hours";
    }

    @RequestMapping(value = "hours/save", consumes = "application/json")
    public
    @ResponseBody
    AlertMessage saveHours(@RequestBody List<OpeningHours> data) {

        for (OpeningHours oh : data) {
            OpeningHours openingHours = openingHoursService.findOne(oh.getId());
            openingHours.setReservationFrom(oh.getReservationFrom());
            openingHours.setReservationTo(oh.getReservationTo());
            openingHours.setReservationMorningFrom(oh.getReservationMorningFrom());
            openingHours.setReservationMorningTo(oh.getReservationMorningTo());

            openingHoursService.save(openingHours);
        }

        return new AlertMessage(AlertMessage.SUCCESS, "Hodiny uložené.");
    }

    @RequestMapping(value = "/employee/{id}")
    public String employee(@PathVariable Long id, Map<String, Object> model) {
        Office office = officeService.findOne(id);
        model.put("office", office);
        return "admin/office/employee";
    }

    @RequestMapping(value = "/insurance/{id}")
    public String insurance(@PathVariable Long id, Map<String, Object> model) {
        Office office = officeService.findOne(id);
        model.put("office", office);
        return "admin/office/insurance";
    }

    @RequestMapping(value = "/specialization/{id}")
    public String specialization(@PathVariable Long id, Map<String, Object> model) {
        Office office = officeService.findOne(id);
        model.put("office", office);
        return "admin/office/specialization";
    }

    @RequestMapping(value = "/specialization/search")
    public
    @ResponseBody
    List<SearchResult> searchSpecialization(@ModelAttribute("searchTerm") String searchTerm) {
        return specializationService.findByName(searchTerm).stream().map(SearchResult::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "specialization/save")
    public
    @ResponseBody
    AlertMessage saveSpecialization(@RequestParam("id") Long id, @RequestParam("specializations") String specializations) {

        Office office = officeService.findOne(id);

        office.getSpecializations().clear();
        for (String specId : specializations.split(",")) {
            if (!specId.isEmpty())
                office.getSpecializations().add(specializationService.findOne(Long.parseLong(specId)));
        }

        officeService.save(office);
        return new AlertMessage(AlertMessage.SUCCESS, "Špecializácie uložené.", office.getId());
    }

    @RequestMapping(value = "/employee/search")
    public
    @ResponseBody
    List<SearchResult> searchEmployee(@ModelAttribute("searchTerm") String searchTerm) {
        return employeeService.findByFirstNameOrLastNameOrUsername(searchTerm).stream().map(SearchResult::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "employee/save")
    public
    @ResponseBody
    AlertMessage saveEmployee(@RequestParam("id") Long id, @RequestParam("employees") String employees) {

        Office office = officeService.findOne(id);

        for (String username : employees.split(",")) {
            if (!username.isEmpty()) {
                Employee employee = employeeService.findOne(username);
                employee.getOffices().add(office);
                employeeService.save(employee);
            }
        }

        return new AlertMessage(AlertMessage.SUCCESS, "Zamestnanci uložený.", office.getId());
    }

    @RequestMapping(value = "employee/remove")
    public
    @ResponseBody
    AlertMessage removeEmployee(@RequestParam("id") Long id, @RequestParam("username") String username) {

        Employee employee = employeeService.findOne(username);
        for (Office office : employee.getOffices()) {
            if (office.getId() == id) {
                employee.getOffices().remove(office);
            }
        }

        employeeService.save(employee);

        return new AlertMessage(AlertMessage.SUCCESS, "Zamestnanec vymazaný.");
    }

    @RequestMapping(value = "/insurance/search")
    public
    @ResponseBody
    List<SearchResult> searchInsurance(@ModelAttribute("searchTerm") String searchTerm) {
        return insuranceService.findByName(searchTerm).stream().map(SearchResult::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "insurance/save")
    public
    @ResponseBody
    AlertMessage saveInsurance(@RequestParam("id") Long id, @RequestParam("insurances") String insurances) {

        Office office = officeService.findOne(id);

        office.getInsurances().clear();
        for (String specId : insurances.split(",")) {
            if (!specId.isEmpty())
                office.getInsurances().add(insuranceService.findOne(Long.parseLong(specId)));
        }

        officeService.save(office);
        return new AlertMessage(AlertMessage.SUCCESS, "Poisťovne uložené.", office.getId());
    }

    private class SearchResult {
        private Long id;
        private String name;
        private String username;

        public SearchResult(Specialization specialization) {
            id = specialization.getId();
            name = specialization.getName();
        }

        public SearchResult(Employee employee) {
            username = employee.getUsername();
            name = employee.getPrefix_title() + " " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getSuffix_title();
        }

        public SearchResult(Insurance insurance) {
            id = insurance.getId();
            name = insurance.getName();
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}

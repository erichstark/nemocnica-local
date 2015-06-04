package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.service.InsuranceService;
import sk.stuba.fei.team.local.service.PatientService;

import java.util.Map;

@Controller
@RequestMapping("/admin/patient")
public class AdminPatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private InsuranceService insuranceService;

    @RequestMapping("")
    public String index(Map<String, Object> model) {
        model.put("patients", patientService.findAll());
        return "admin/patient/index";
    }

    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {
        model.put("patient", new Patient());
        model.put("insurances", insuranceService.findAll());
        return "admin/patient/add";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Map<String, Object> model) {
        model.put("patient", patientService.findOne(id));
        model.put("insurances", insuranceService.findAll());

        return "admin/patient/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAdd(@ModelAttribute("patient") Patient patient, @RequestParam Long id_insurance) {
        patient.setInsurance(insuranceService.findOne(id_insurance));
        patientService.save(patient);
        return "redirect:/admin/patient";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        patientService.delete(id);
        return "redirect:/admin/patient";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("text") String text, Map<String, Object> model) {
        Iterable<Patient> patients = patientService.findPatientByFirstOrSurname(text);
        model.put("search", text);
        model.put("patients", patients);
        return "admin/patient/index";
    }

    @RequestMapping(value = "/clear")
    public String clear(Map<String, Object> model) {
        model.put("patients", patientService.findAll());
        return "admin/patient/index";
    }
}

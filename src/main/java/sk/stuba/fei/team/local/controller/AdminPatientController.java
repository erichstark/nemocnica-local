package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.team.local.api.domain.PatientWrapper;
import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.repository.InsuranceRepository;
import sk.stuba.fei.team.local.repository.PatientRepository;
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
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

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
    public String edit(@PathVariable String id, Map<String, Object> model) {
        model.put("patient", patientService.findOne(id));
        model.put("insurances", insuranceService.findAll());

        return "admin/patient/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAdd(@ModelAttribute("patient") PatientWrapper patientWrapper) {
        Patient patient = patientWrapper.build(patientRepository,insuranceService);
        patientService.save(patient);
        return "redirect:/admin/patient";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        //todo Id je string username
//        patientService.delete(id);
        return "redirect:/admin/patient";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("text") String text, Map<String, Object> model) {
        Iterable<Patient> patients = patientService.findBySurNameOrEmail(text);
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

package sk.stuba.fei.team.local.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.domain.Facility;
import sk.stuba.fei.team.local.service.EmployeeService;
import sk.stuba.fei.team.local.service.FacilityService;
import sk.stuba.fei.team.local.service.InsuranceService;
import sk.stuba.fei.team.local.service.SpecializationService;

import java.util.Map;

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
    @Autowired
    RestConsumer restConsumer;

    private Logger logger = LoggerFactory.getLogger(AdminFacilityController.class);

    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public String setup() {
        return "/admin/facility/setup";
    }

    @RequestMapping(value = "/setup", method = RequestMethod.POST)
    private
    @ResponseBody
    Boolean setup(@ModelAttribute("facility") Facility facility) {
        try {
            restConsumer.configure(facility);
            facilityService.save(facility);
            insuranceService.update();
            specializationService.update();
        } catch (Exception e) {
            logger.error("Failed to setup system.", e);
            return false;
        }
        return true;
    }

    @RequestMapping(value = "admin/facility", method = RequestMethod.GET)
    public String edit(Map<String, Object> model) {
        model.put("facility", facilityService.getFacility());
        return "admin/facility/index";
    }


    @RequestMapping(value = "admin/facility", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean save(@ModelAttribute(value = "facility") Facility facility) {
        Facility oldFacility = facilityService.getFacility();
        oldFacility.setName(facility.getName());
        oldFacility.setStreetAndNumber(facility.getStreetAndNumber());
        oldFacility.setZip(facility.getZip());
        oldFacility.setCity(facility.getCity());
        oldFacility.setClientID(facility.getClientID());
        oldFacility.setClientSecret(facility.getClientSecret());
        oldFacility.setUsername(facility.getUsername());
        oldFacility.setPassword(facility.getPassword());
        facilityService.save(oldFacility);
        return true;
    }
}

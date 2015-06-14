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
    Boolean save(@ModelAttribute("facility") Facility facility) {
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
}

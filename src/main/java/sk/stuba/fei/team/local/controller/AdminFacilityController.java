package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.stuba.fei.team.local.domain.Facility;
import sk.stuba.fei.team.local.service.FacilityService;

import java.util.Map;

@Controller
@RequestMapping(value = "admin/facility")
public class AdminFacilityController {

    @Autowired
    private FacilityService facilityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String edit(Map<String, Object> model) {
        model.put("facility", facilityService.getFacility());
        return "admin/facility/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
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
        facilityService.save(oldFacility);
        return true;
    }
}

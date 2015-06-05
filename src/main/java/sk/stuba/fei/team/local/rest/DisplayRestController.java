package sk.stuba.fei.team.local.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stuba.fei.team.local.domain.Advertisement;
import sk.stuba.fei.team.local.domain.DisplayConfiguration;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Specialization;
import sk.stuba.fei.team.local.service.AdvertisementService;
import sk.stuba.fei.team.local.service.DisplayConfigurationService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("service/display")
public class DisplayRestController {

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    DisplayConfigurationService displayConfigurationService;

    @RequestMapping("/{id}")
    public RestMessage getConfiguration(@PathVariable String id) {
        DisplayConfiguration display = displayConfigurationService.findOne(id);
        Set<Specialization> specializations = new HashSet<>();
        if (display != null) {
            Set<String> fileNames = new HashSet<>();
            for (Office office : display.getOffices()) {
                specializations.addAll(office.getSpecializations());
            }
            if(specializations.isEmpty()) {
                Iterable<Advertisement> advertisements = advertisementService.findBySpecializationsIn(specializations);
                if (advertisements != null) {
                    for (Advertisement ad : advertisements) {
                        fileNames.add(ad.getFilename());
                    }
                }
            }
            return new RpiConfigMessage(fileNames);
        } else {
            return new RestErrorMessage(1, "No display found with given ID:" + id);
        }
    }

}

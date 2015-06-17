package sk.stuba.fei.team.local.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stuba.fei.team.local.domain.DisplayConfiguration;
import sk.stuba.fei.team.local.service.DisplayConfigurationService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("service/display")
public class DisplayRestController {

    @Autowired
    DisplayConfigurationService displayConfigurationService;

    @RequestMapping("/{id}")
    public RestMessage getConfiguration(@PathVariable String id) {
        DisplayConfiguration display = displayConfigurationService.findOne(id);
        if (display != null) {
            return new RpiConfigMessage(display.getOffices().stream().map(RpiOffice::new).collect(Collectors.toSet()));
        } else {
            return new RestErrorMessage(1, "No display found with given ID:" + id);
        }
    }

}

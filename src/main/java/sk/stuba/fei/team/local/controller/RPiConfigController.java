package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.stuba.fei.team.local.JmsProducer;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Rpi_Office;

import java.util.*;

@Controller
@RequestMapping("/admin/rpiconfig")
public class RPiConfigController {

    @Autowired
    JmsProducer jmsProducer;

    @RequestMapping("")
    public String index(Map<String, Object> model) {
        model.put("pageTitle", "Admin Rpi");
        List<Rpi_Office> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Set<Office> officeSet = new HashSet<>();
            for (int j = 1; j <= new Random().nextInt(3); j++) {
                officeSet.add(new Office("office" + j));
            }
            list.add(new Rpi_Office("rpi" + i, officeSet));
        }
        model.put("rpiList", list);
        return "admin/rpiconfig/index";
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Rpi_Office getRPiConfiguration(@PathVariable("id") String id) {

        return null;
    }
}

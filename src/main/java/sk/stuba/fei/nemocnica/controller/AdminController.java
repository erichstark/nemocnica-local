package sk.stuba.fei.nemocnica.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by matus_000 on 31.3.2015.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    @RequestMapping(value = "/admin")
    public ModelAndView showAdmin() {
        return new ModelAndView("admin");
    }
}

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
@PreAuthorize("hasRole('ROLE_USER')")
public class HelloWorldController {
    String message = "Welcome to Spring MVC!";

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView showMessage(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        ModelAndView mv = new ModelAndView("helloworld");
        mv.addObject("message", message);
        mv.addObject("name", name);
        return mv;
    }
}

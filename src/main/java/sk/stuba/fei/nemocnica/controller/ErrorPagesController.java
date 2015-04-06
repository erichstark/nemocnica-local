package sk.stuba.fei.nemocnica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by matus_000 on 6.4.2015.
 */
@Controller
public class ErrorPagesController {

    @RequestMapping(value = "/403")
    public ModelAndView show403() {
        return new ModelAndView("403");
    }
}

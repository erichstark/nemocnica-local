package sk.stuba.fei.nemocnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sk.stuba.fei.nemocnica.dao.ZamestnanecDAO;

/**
 * Created by matus_000 on 10.4.2015.
 */
@Controller
public class ZamestnanciController {

    @Autowired
    ZamestnanecDAO zamestnanecDAO;

    @RequestMapping(value = "/admin/zamestnanci")
    public ModelAndView showZamestnanci() {
        ModelAndView mv = new ModelAndView("admin/zamestnanci");
        mv.addObject("zamestnanci", zamestnanecDAO.findAll());
        return mv;
    }
}

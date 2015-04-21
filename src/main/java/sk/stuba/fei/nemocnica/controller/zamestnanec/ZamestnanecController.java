package sk.stuba.fei.nemocnica.controller.zamestnanec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import sk.stuba.fei.nemocnica.dao.ZamestnanecDAO;
import sk.stuba.fei.nemocnica.model.Zamestnanec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakubrehak on 20/04/15.
 */

@Controller
@SessionAttributes( "zamestnanciSearchForm")
public class ZamestnanecController {


    @Autowired
    ZamestnanecDAO zamestnanecDAO;

    @ModelAttribute("zamestnanciSearchForm")
    public ZamestnanciSearchForm getZamestnanciSearchForm() {
        ZamestnanciSearchForm zamestnanciSearchForm = new ZamestnanciSearchForm();
        zamestnanciSearchForm.setName("");
        zamestnanciSearchForm.setSurname("");
        zamestnanciSearchForm.setSortField("name");
        zamestnanciSearchForm.setSortOrder("DESC");
        return zamestnanciSearchForm;
    }

    @RequestMapping(value = "/lekari")
    public ModelAndView showLekari(@ModelAttribute("zamestnanciSearchForm") ZamestnanciSearchForm searchForm) {

        ModelAndView mv = new ModelAndView("lekar/lekari");
        searchForm.setBaseLink("/lekari");
        List<String> spec= new ArrayList();
        spec=zamestnanecDAO.findDoctors("%"+searchForm.getName()+"%"+searchForm.getSurname()+"%","%"+searchForm.getSpecialization()+"%",searchForm.getTown()).get(1).getSpecializations();
        mv.addObject("lekari", zamestnanecDAO.findDoctors("%"+searchForm.getName()+"%"+searchForm.getSurname()+"%","%"+searchForm.getSpecialization()+"%",searchForm.getTown()));
        mv.addObject("spec",spec);
        return mv;
    }

    @RequestMapping(value = "/lekar/{id}")
    public ModelAndView showLekar(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("lekari/lekar");
        mv.addObject("lekar", zamestnanecDAO.findById(id));
        return mv;
    }




}

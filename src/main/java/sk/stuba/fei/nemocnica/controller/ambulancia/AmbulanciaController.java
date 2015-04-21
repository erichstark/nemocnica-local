package sk.stuba.fei.nemocnica.controller.ambulancia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sk.stuba.fei.nemocnica.dao.AmbulanciaDAO;
import sk.stuba.fei.nemocnica.model.Ambulancia;

/**
 * Created by jakubrehak on 19/04/15.
 */
@Controller
@SessionAttributes( "ambulancieSearchForm")
public class AmbulanciaController {

    @Autowired
    AmbulanciaDAO ambulanciaDAO;

    @ModelAttribute("ambulancieSearchForm")
    public AmbulancieSearchForm getAmbulancieSearchForm() {
        AmbulancieSearchForm ambulancieSearchForm = new AmbulancieSearchForm();
        ambulancieSearchForm.setMeno("");
        ambulancieSearchForm.setSortField("name");
        ambulancieSearchForm.setSortOrder("DESC");
        return ambulancieSearchForm;
    }

    @RequestMapping(value = "/ambulancia/ambulancie")
    public ModelAndView showAmbulancie(@ModelAttribute("ambulancieSearchForm") AmbulancieSearchForm searchForm) {

        ModelAndView mv = new ModelAndView("ambulancia/ambulancie");
        searchForm.setBaseLink( "/ambulancia/ambulancie"  );
        mv.addObject("ambulancie", ambulanciaDAO.findAll(searchForm.getMeno()+"%"));
        return mv;
    }

    @RequestMapping(value = "/ambulancia/{id}")
    public ModelAndView showAmbulancia(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("ambulancia/ambulancia");
        mv.addObject("ambulancia", ambulanciaDAO.findById(id));
        return mv;
    }

}

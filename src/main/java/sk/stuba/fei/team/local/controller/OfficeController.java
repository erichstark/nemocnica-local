package sk.stuba.fei.team.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.team.local.api.AlertMessage;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.OpeningHours;
import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.jms.CallInMessage;
import sk.stuba.fei.team.local.jms.JmsProducer;
import sk.stuba.fei.team.local.service.AppointmentService;
import sk.stuba.fei.team.local.service.OfficeService;
import sk.stuba.fei.team.local.service.OpeningHoursService;
import sk.stuba.fei.team.local.service.PatientService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    JmsProducer jmsProducer;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private OpeningHoursService openingHoursService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/{id}")
    public String index(@PathVariable Long id, Map<String, Object> model) {
        model.put("office", officeService.findOne(id));
        return "office/index";
    }

    @RequestMapping(value = "/hours/save", consumes = "application/json")
    public
    @ResponseBody
    AlertMessage saveHours(@RequestBody List<OpeningHours> data) {

        for (OpeningHours oh : data) {
            OpeningHours openingHours = openingHoursService.findOne(oh.getId());
            openingHours.setReservationFrom(oh.getReservationFrom());
            openingHours.setReservationTo(oh.getReservationTo());
            openingHours.setReservationMorningFrom(oh.getReservationMorningFrom());
            openingHours.setReservationMorningTo(oh.getReservationMorningTo());

            openingHoursService.save(openingHours);
        }

        return new AlertMessage(AlertMessage.SUCCESS, "Hodiny uložené.");
    }

    @RequestMapping(value = "/{id}/call")
    public String callPationt(@PathVariable Long id, Map<String, Object> model) {
        Office office = officeService.findOne(id);
        Date date = new Date();

        model.put("office", office);
        model.put("appointments", appointmentService.findByDateAndOffice(clearTimeInDate(date, 0), office));

        return "office/call";
    }

    @RequestMapping(value = "/{id}/patientdetail/{username}")
    public String patientDetail(@PathVariable("id") Long id, @PathVariable("username") String username, Map<String, Object> model) {
        Patient patient = patientService.findOne(username);

        model.put("office_id", id);
        model.put("patient", patient);
        model.put("appointments", appointmentService.findByPatient(patient));

        return "office/patient";
    }

    @RequestMapping(value = "/{id}/orders")
    public String orders(@PathVariable Long id, Map<String, Object> model) {
        Office office = officeService.findOne(id);
        Date date = new Date();

        model.put("office", office);
        model.put("appointments", appointmentService.findByDateAfterAndOffice(clearTimeInDate(date, -1), office));

        return "office/orders";
    }

    @RequestMapping(value = "/invoke")
    public
    @ResponseBody
    AlertMessage invoke(@RequestParam("id") Long id, @RequestParam("str") String str) {
        CallInMessage callInMessage = new CallInMessage();
        callInMessage.setOffice(id);
        callInMessage.setPatient(str);

        jmsProducer.sendMessage(callInMessage);

        return new AlertMessage(AlertMessage.SUCCESS, "Pacient vyvolaný.");
    }

    private Date clearTimeInDate(Date date, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}

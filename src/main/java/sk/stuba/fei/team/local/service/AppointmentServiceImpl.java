package sk.stuba.fei.team.local.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.UpdateWrapper;
import sk.stuba.fei.team.local.api.domain.AppointmentWrapper;
import sk.stuba.fei.team.local.domain.Appointment;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.repository.AppointmentRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component("appointmentService")
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private RestConsumer restConsumer;

    @Autowired
    private FacilityService facilityService;

    @Override
    public void save(Appointment appointment) {
        Long id = (Long) restConsumer.post("appointment", new AppointmentWrapper(appointment), Long.class);
        appointment.setId(id);
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findByDateAndOfficeAndEnabled(Date date, Office office) {
        return appointmentRepository.findByDateAndOfficeAndEnabledTrue(date, office);
    }

    @Override
    public List<Appointment> findByDateAfterAndOffice(Date date, Office office) {
        return appointmentRepository.findByDateAfterAndOffice(date, office);
    }

    @Override
    public Iterable<Appointment> findAll() {

        return appointmentRepository.findAll();
    }

    @Override
    public Iterable<Appointment> findByPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findOne(id);
    }

    @Override
    public void update() {
        AppointmentWrapper[] appointmentWrappers = (AppointmentWrapper[]) restConsumer.post("appointment/update", new UpdateWrapper<>(facilityService.getFacility().getOffices().stream().map(Office::getId).collect(Collectors.toSet()), facilityService.getAppointmentsUpdateDate()), AppointmentWrapper[].class);
        for (AppointmentWrapper appointmentWrapper : appointmentWrappers) {
            appointmentRepository.save(appointmentWrapper.build(patientService, officeService));
        }
        facilityService.appointmentsUpdated();
    }
}

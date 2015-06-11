package sk.stuba.fei.team.local.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.domain.Appointment;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.repository.AppointmentRepository;

import java.util.Date;
import java.util.List;

@Component("appointmentService")
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findByDateAndOffice(Date date, Office office) {
        return appointmentRepository.findByDateAndOffice(date, office);
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
}

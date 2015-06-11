package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Appointment;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Patient;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    void save(Appointment appointment);

    List<Appointment> findByDateAndOffice(Date date, Office office);

    Iterable<Appointment> findAll();

    Iterable<Appointment> findByPatient(Patient patient);

    Appointment findById(Long id);

}

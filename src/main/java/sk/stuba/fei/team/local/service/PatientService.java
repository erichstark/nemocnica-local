package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Patient;

import java.util.List;

public interface PatientService {

    void save(Patient patient);

    Patient findOne(String id);

    Iterable<Patient> findAll();

    boolean exists(String id);

    void delete(String id);

    List<Patient> findPatientByFirstOrSurname(String text);
}

package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Patient;

import java.util.List;

public interface PatientService {

    void save(Patient patient);

    Patient findOne(String username);

    Iterable<Patient> findAll();

    boolean exists(String id);

    List<Patient> findBySurNameOrEmail(String searchTerm);

    void update();

}

package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Patient;

import java.util.List;

/**
 * Created by pallo on 5/11/15.
 */
public interface PatientService {

    void save(Patient patient);

    Patient findOne(Long id);

    Iterable<Patient> findAll();

    boolean exists(Long id);

    void delete(Long id);

    List<Patient> findPatientByFirstOrSurname(String text);
}

package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.repository.PatientRepository;

import java.util.List;

@Component("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public Patient findOne(String id) {
        return patientRepository.findOne(id);
    }

    @Override
    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public boolean exists(String id) {
        return patientRepository.exists(id);
    }

    @Override
    public void delete(String id) {
        patientRepository.delete(id);
    }

    @Override
    public List<Patient> findPatientByFirstOrSurname(String text) {
        return patientRepository.findByFirstnameOrSerunameCustomQuery(text);
    }

}

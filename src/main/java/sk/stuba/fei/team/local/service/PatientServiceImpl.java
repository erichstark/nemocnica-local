package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.UpdateWrapper;
import sk.stuba.fei.team.local.api.domain.PatientWrapper;
import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

@Component("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private RestConsumer restConsumer;

    @Override
    public void save(Patient patient) {
        restConsumer.post("patient", new PatientWrapper(patient), String.class);
        patientRepository.save(patient);
    }

    @Override
    public Patient findOne(String username) {
        Patient one = patientRepository.findOne(username);
        if (one == null) {
            PatientWrapper patientWrapper = (PatientWrapper) restConsumer.get("patient/" + username, PatientWrapper.class);
            if (patientWrapper != null) {
                return patientWrapper.build(this, insuranceService);
            }
        }
        return one;
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
    public List<Patient> findBySurNameOrEmail(String searchTerm) {
        List<Patient> patients = new ArrayList<>();
        PatientWrapper[] patientWrappers = (PatientWrapper[]) restConsumer.get("patient/find/" + searchTerm, PatientWrapper[].class);
        for (PatientWrapper patientWrapper : patientWrappers) {
            patients.add(patientWrapper.build(this, insuranceService));
        }
        return patients;
    }

    @Override
    public void update() {
        List<String> usernames = new ArrayList<>();
        patientRepository.findAll().forEach(p -> usernames.add(p.getUsername()));
        PatientWrapper[] patientWrappers = (PatientWrapper[]) restConsumer.post("patient/update", new UpdateWrapper<>(usernames, facilityService.getPatientsUpdateDate()), PatientWrapper[].class);
        for (PatientWrapper patientWrapper : patientWrappers) {
            patientRepository.save(patientWrapper.build(this, insuranceService));
        }
    }
}

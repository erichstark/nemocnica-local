package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.domain.Facility;
import sk.stuba.fei.team.local.repository.FacilityRepository;

import java.util.Date;

@Component("facilityService")
@Transactional
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private RestConsumer restConsumer;

    @Override
    public void save(Facility facility) {
        Long id = (Long) restConsumer.post("", facility, Long.class);
        facility.setId(id);
        facilityRepository.save(facility);
    }

    @Override
    public Date getEmployeesUpdateDate() {
        return getFacility().getEmployeesUpdated();
    }

    @Override
    public Date getPatientsUpdateDate() {
        return getFacility().getPatientsUpdated();
    }

    @Override
    public Date getAppointmentsUpdateDate() {
        return getFacility().getAppointmentsUpdated();
    }

    @Override
    public Date getSpecializationsUpdateDate() {
        return getFacility().getSpecializationsUpdated();
    }

    @Override
    public Date getInsurancesUpdateDate() {
        return getFacility().getInsurancesUpdated();
    }

    @Override
    public void employeesUpdated() {
        Facility f = getFacility();
        f.setEmployeesUpdated(new Date());
        facilityRepository.save(f);
    }

    @Override
    public void patientsUpdated() {
        Facility f = getFacility();
        f.setPatientsUpdated(new Date());
        facilityRepository.save(f);
    }

    @Override
    public void appointmentsUpdated() {
        Facility f = getFacility();
        f.setEmployeesUpdated(new Date());
        facilityRepository.save(f);
    }

    @Override
    public void specializationsUpdated() {
        Facility f = getFacility();
        f.setSpecializationsUpdated(new Date());
        facilityRepository.save(f);
    }

    @Override
    public void insurancesUpdated() {
        Facility f = getFacility();
        f.setInsurancesUpdated(new Date());
        facilityRepository.save(f);
    }

    @Override
    public Facility getFacility() {
        return facilityRepository.findAll().iterator().next();
    }
}

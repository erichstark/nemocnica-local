package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Facility;

import java.util.Date;

public interface FacilityService {

    void save(Facility facility);

    Date getEmployeesUpdateDate();

    Date getPatientsUpdateDate();

    Date getAppointmentsUpdateDate();

    Date getSpecializationsUpdateDate();

    Date getInsurancesUpdateDate();

    void employeesUpdated();

    void patientsUpdated();

    void appointmentsUpdated();

    void specializationsUpdated();

    void insurancesUpdated();

    Facility getFacility();

}

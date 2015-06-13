package sk.stuba.fei.team.local.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("syncService")
public class SyncService {
    private static final int MIN_WAIT_TIME = 5;//minutes
    private Logger logger = LoggerFactory.getLogger(SyncService.class);
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private AppointmentService appointmentService;

    @Scheduled(fixedDelay = 1000 * 60 * MIN_WAIT_TIME / 2, initialDelay = 1000)
    public void sync() {
        if (facilityService.getFacility() != null) {
            Date now = new Date();
            logger.info("Starting synchronization...");

            long diff = now.getTime() - facilityService.getInsurancesUpdateDate().getTime();
            if ((diff / (60 * 1000) % 60) > MIN_WAIT_TIME) {
                insuranceService.update();
                logger.info("Sync of Insurances: DONE");
            } else {
                logger.info("Sync of Insurances: SKIPPED");
            }

            diff = now.getTime() - facilityService.getSpecializationsUpdateDate().getTime();
            if ((diff / (60 * 1000) % 60) > MIN_WAIT_TIME) {
                specializationService.update();
                logger.info("Sync of Specializations: DONE");
            } else {
                logger.info("Sync of Specializations: SKIPPED");
            }

            diff = now.getTime() - facilityService.getEmployeesUpdateDate().getTime();
            if ((diff / (60 * 1000) % 60) > MIN_WAIT_TIME) {
                employeeService.update();
                logger.info("Sync of Employees: DONE");
            } else {
                logger.info("Sync of Employees: SKIPPED");
            }

            diff = now.getTime() - facilityService.getPatientsUpdateDate().getTime();
            if ((diff / (60 * 1000) % 60) > MIN_WAIT_TIME) {
                patientService.update();
                logger.info("Sync of Patients: DONE");
            } else {
                logger.info("Sync of Patiens: SKIPPED");
            }
        }
    }
}

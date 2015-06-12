package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.domain.OfficeWrapper;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.repository.OfficeRepository;

import java.util.List;

@Component("officeService")
@Transactional
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private FacilityService facilityService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private RestConsumer restConsumer;

    @Override
    public Office findOne(Long id) {
        return officeRepository.findOne(id);
    }

    @Override
    public List<Office> findByName(String name) {
        return officeRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Iterable<Office> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return officeRepository.exists(id);
    }

    @Override
    public void save(Office office) {
        Long id = (Long) restConsumer.post("office", new OfficeWrapper(office), Long.class);
        office.setId(id);
        officeRepository.save(office);
    }

    @Override
    public void delete(Long id) {
        officeRepository.delete(id);
    }

    @Override
    public List<Office> findByIdOrNameOrEmployeesName(String searchTerm) {
        Long id;
        try {
            id = Long.parseLong(searchTerm);
        } catch (NumberFormatException ex) {
            id = (long) -1;
        }
        return officeRepository.findDistinctOfficesByNameContainingIgnoreCaseOrIdOrEmployeesIn(searchTerm, id, employeeService.findByFirstNameOrLastNameOrUsername(searchTerm));
    }
}

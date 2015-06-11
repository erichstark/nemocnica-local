package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.UpdateWrapper;
import sk.stuba.fei.team.local.api.domain.EmployeeWrapper;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final String FIND_BY_USERNAME = "employee/%s";
    private static final String UPDATE = "employee/update";
    private static final String SAVE = "employee";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestConsumer restConsumer;

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private SpecializationService specializationService;


    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
        restConsumer.post(SAVE, new EmployeeWrapper(employee), String.class);
    }

    @Override
    public Employee findOne(String username) {
        Employee employee = employeeRepository.findOne(username);
        if (employee != null) {
            return employee;
        } else {
            EmployeeWrapper employeeWrapper = (EmployeeWrapper) restConsumer.get(String.format(FIND_BY_USERNAME, username), EmployeeWrapper.class);
            return employeeWrapper.build(specializationService, this);
        }
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean exists(String username) {
        return restConsumer.get(String.format(FIND_BY_USERNAME, username), EmployeeWrapper.class) != null;
    }

    @Override
    public List<Employee> findByFirstNameOrLastNameOrUsername(String text) {
        return employeeRepository.findByFirstNameContainingOrLastNameContainingOrUsernameContainingAllIgnoreCase(text, text, text);
    }

    @Override
    public void update() {
        Collection<String> usernames = new ArrayList<>();
        for (Employee em : employeeRepository.findAll()) {
            usernames.add(em.getUsername());
        }
        EmployeeWrapper[] employees = (EmployeeWrapper[]) restConsumer.post(UPDATE, new UpdateWrapper<>(usernames, facilityService.getEmployeesUpdateDate()), EmployeeWrapper[].class);
        for (EmployeeWrapper employeeWrapper : employees) {
            employeeRepository.save(employeeWrapper.build(specializationService, this));
        }
        facilityService.employeesUpdated();
    }
}

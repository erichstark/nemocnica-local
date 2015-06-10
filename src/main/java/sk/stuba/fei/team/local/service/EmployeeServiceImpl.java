package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.UpdateWrapper;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findOne(username);
//        return (Employee) restConsumer.get(String.format(FIND_BY_USERNAME, username), Employee.class);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
        employee.setAuthorities(Collections.emptySet());
        restConsumer.post(SAVE, employee, String.class);
    }

    @Override
    public Employee findOne(String username) {
        return employeeRepository.findOne(username);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean exists(String username) {
        return restConsumer.get(FIND_BY_USERNAME + username, Employee.class) != null;
    }

    @Override
    public void delete(String username) {
        employeeRepository.delete(username);
    }

    @Override
    public List<Employee> findPatientByUsernameOrFirstOrSurname(String text) {
        return employeeRepository.findByUsernameOrFirstnameOrSerunameCustomQuery(text);
    }

    @Override
    public List<Employee> findByFirstNameOrLastName(String text) {
        return employeeRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(text, text);
    }

    @Override
    public void update() {
        Collection<String> usernames = new ArrayList<>();
        for (Employee em : employeeRepository.findAll()) {
            usernames.add(em.getUsername());
        }
        Employee[] employees = (Employee[]) restConsumer.post(UPDATE, new UpdateWrapper<>(usernames, facilityService.getEmployeesUpdateDate()), Employee[].class);
        for (Employee newEmployee : employees) {
            Employee oldEmployee = employeeRepository.findByUsername(newEmployee.getUsername());
            if (oldEmployee != null) {
                newEmployee.setAuthorities(oldEmployee.getAuthorities());
                newEmployee.setOffices(oldEmployee.getOffices());
            }
            employeeRepository.save(newEmployee);
        }
        facilityService.employeesUpdated();
    }
}

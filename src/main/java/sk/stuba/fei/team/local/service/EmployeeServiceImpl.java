package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.repository.EmployeeRepository;

import java.util.List;

@Component("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
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
        return employeeRepository.exists(username);
    }

    @Override
    public void delete(String username) {
        employeeRepository.delete(username);
    }

    @Override
    public List<Employee> findPatientByUsernameOrFirstOrSurname(String text) {
        return employeeRepository.findByUsernameOrFirstnameOrSerunameCustomQuery(text);
    }
}

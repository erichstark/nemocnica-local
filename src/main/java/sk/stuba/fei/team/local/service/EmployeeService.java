package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findByUsername(String username);

    void save(Employee employee);

    Employee findOne(String username);

    Iterable<Employee> findAll();

    boolean exists(String username);

    void delete(String username);

    List<Employee> findPatientByUsernameOrFirstOrSurname(String text);

    List<Employee> findByFirstNameOrLastName(String text);

    void update();
}

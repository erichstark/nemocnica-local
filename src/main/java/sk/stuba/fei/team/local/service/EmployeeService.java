package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Employee;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);

    Employee findOne(String username);

    Iterable<Employee> findAll();

    boolean exists(String username);

    List<Employee> findByFirstNameOrLastName(String text);

    void update();
}

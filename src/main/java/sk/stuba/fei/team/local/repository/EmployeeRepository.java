package sk.stuba.fei.team.local.repository;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.team.local.domain.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Employee findByUsername(String username);

    List<Employee> findByFirstNameContainingOrLastNameContainingOrUsernameContainingAllIgnoreCase(String firstName, String lastName, String username);
}

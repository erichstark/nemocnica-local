package sk.stuba.fei.team.local.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sk.stuba.fei.team.local.domain.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Employee findByUsername(String username);

    @Query("select e from Employee e where e.username like %:text% or e.firstName like %:text% or e.lastName like %:text%")
    List<Employee> findByUsernameOrFirstnameOrSerunameCustomQuery(@Param("text") String text);

    List<Employee> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstName, String lastName);
}

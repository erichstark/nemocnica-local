package sk.stuba.fei.team.local.repository;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Specialization;

import java.util.List;

public interface OfficeRepository extends CrudRepository<Office, Long> {

    List<Office> findByNameContainingIgnoreCase(String name);

    List<Office> findDistinctOfficesByNameContainingIgnoreCaseOrEmployeesInOrSpecializationsIn(String name, List<Employee> employees, List<Specialization> specializations);

}

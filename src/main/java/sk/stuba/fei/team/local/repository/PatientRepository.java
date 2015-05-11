package sk.stuba.fei.team.local.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.team.local.domain.Patient;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pallo on 5/11/15.
 */
public interface PatientRepository extends CrudRepository<Patient, Long> {


    @Query("select p from Patient p where p.firstName like %:text% or p.surname like %:text%")
    List<Patient> findByFirstnameOrSerunameCustomQuery(@Param("text") String text);

}

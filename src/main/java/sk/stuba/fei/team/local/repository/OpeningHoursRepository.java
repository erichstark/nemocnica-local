package sk.stuba.fei.team.local.repository;


import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.team.local.domain.OpeningHours;

import java.util.List;

public interface OpeningHoursRepository extends CrudRepository<OpeningHours, Long> {

    List<OpeningHours> findByOfficeId(Long id);

}

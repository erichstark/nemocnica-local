package sk.stuba.fei.nemocnica.repository;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.nemocnica.domain.Zariadenie;

/**
 * Created by pallo on 5/3/15.
 */
public interface ZariadenieRepository extends CrudRepository<Zariadenie, Long> {
}

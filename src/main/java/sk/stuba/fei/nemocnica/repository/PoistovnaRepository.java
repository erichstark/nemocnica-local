package sk.stuba.fei.nemocnica.repository;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.nemocnica.domain.Poistovna;

/**
 * Created by pallo on 5/3/15.
 */
public interface PoistovnaRepository extends CrudRepository<Poistovna, Long> {
}

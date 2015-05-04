package sk.stuba.fei.nemocnica.service;

import sk.stuba.fei.nemocnica.domain.Poistovna;

/**
 * Created by pallo on 5/3/15.
 */
public interface PoistovnaService {

    Poistovna findOne(Long id);

    Iterable<Poistovna> findAll();

    boolean exists(Long id);

    void save(Poistovna ambulancia);

    void delete(Long id);
}

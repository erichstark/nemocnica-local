package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Office;

import java.util.List;

public interface OfficeService {

    Office findOne(Long id);

    List<Office> findByName(String name);

    Iterable<Office> findAll();

    boolean exists(Long id);

    void save(Office office);

    void delete(Long id);

    List<Office> findByNameOrEmployeeOrSpecialization(String searchTerm);

}

package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.DisplayConfiguration;

public interface DisplayConfigurationService {
    DisplayConfiguration findOne(String id);

    Iterable<DisplayConfiguration> findAll();

    boolean exists(String id);

    void save(DisplayConfiguration rpiConfiguration);

    void delete(String id);

}

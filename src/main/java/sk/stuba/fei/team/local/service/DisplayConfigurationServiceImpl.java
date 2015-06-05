package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.domain.DisplayConfiguration;
import sk.stuba.fei.team.local.repository.DisplayConfigurationRepository;

@Component("displayConfigurationService")
@Transactional
public class DisplayConfigurationServiceImpl implements DisplayConfigurationService {

    @Autowired
    DisplayConfigurationRepository displayConfigurationRepository;


    @Override
    public DisplayConfiguration findOne(String id) {
        return displayConfigurationRepository.findOne(id);
    }

    @Override
    public Iterable<DisplayConfiguration> findAll() {
        return displayConfigurationRepository.findAll();
    }

    @Override
    public boolean exists(String id) {
        return displayConfigurationRepository.exists(id);
    }

    @Override
    public void save(DisplayConfiguration rpiConfiguration) {
        displayConfigurationRepository.save(rpiConfiguration);
    }

    @Override
    public void delete(String id) {
        displayConfigurationRepository.delete(id);
    }
}

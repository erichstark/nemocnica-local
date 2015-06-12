package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.domain.SpecializationWrapper;
import sk.stuba.fei.team.local.domain.Specialization;
import sk.stuba.fei.team.local.repository.SpecializationRepository;

import java.util.List;

@Component("specializationService")
@Transactional
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private RestConsumer restConsumer;

    @Override
    public Specialization findOne(Long id) {
        return specializationRepository.findOne(id);
    }

    @Override
    public List<Specialization> findByName(String name) {
        return specializationRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Iterable<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return specializationRepository.exists(id);
    }

    @Override
    public void update() {
        SpecializationWrapper[] specializations = (SpecializationWrapper[]) restConsumer.post("insurance/update", facilityService.getSpecializationsUpdateDate(), SpecializationWrapper[].class);
        for (SpecializationWrapper specializationWrapper : specializations) {
            specializationRepository.save(specializationWrapper.build(this));
        }
        facilityService.specializationsUpdated();
    }
}

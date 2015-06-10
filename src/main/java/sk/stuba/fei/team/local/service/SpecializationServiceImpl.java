package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
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
        Specialization[] specializations = (Specialization[]) restConsumer.post("insurance/update", facilityService.getSpecializationsUpdateDate(), Specialization[].class);
        for (Specialization newSpecialization : specializations) {
            Specialization oldSpecialization = specializationRepository.findOne(newSpecialization.getId());
            if (oldSpecialization != null) {
                newSpecialization.setEmployees(oldSpecialization.getEmployees());
                newSpecialization.setOffices(oldSpecialization.getOffices());
            }
            specializationRepository.save(newSpecialization);
        }
        facilityService.specializationsUpdated();
    }
}

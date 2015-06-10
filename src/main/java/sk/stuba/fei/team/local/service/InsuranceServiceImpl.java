package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.domain.InsuranceWrapper;
import sk.stuba.fei.team.local.domain.Insurance;
import sk.stuba.fei.team.local.repository.InsuranceRepository;

import java.util.List;

@Component("insuranceService")
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private RestConsumer restConsumer;

    @Autowired
    private FacilityService facilityService;

    @Override
    public Insurance findOne(Long id) {
        return insuranceRepository.findOne(id);
    }

    @Override
    public List<Insurance> findByName(String name) {
        return insuranceRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Iterable<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return insuranceRepository.exists(id);
    }

    @Override
    public void update() {
        InsuranceWrapper[] insurances = (InsuranceWrapper[]) restConsumer.post("insurance/update", facilityService.getInsurancesUpdateDate(), InsuranceWrapper[].class);
        for (InsuranceWrapper insurance : insurances) {
            insuranceRepository.save(insurance.build(this));
        }
        facilityService.insurancesUpdated();
    }
}

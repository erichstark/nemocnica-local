package sk.stuba.fei.team.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.stuba.fei.team.local.domain.Advertisement;
import sk.stuba.fei.team.local.domain.Specialization;
import sk.stuba.fei.team.local.repository.AdvertisementRepository;

import java.util.Collection;

@Component("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    public AdvertisementRepository advertisementRepository;

    @Override
    public Advertisement findOne(Long id) {
        return advertisementRepository.findOne(id);
    }

    @Override
    public Iterable<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return advertisementRepository.exists(id);
    }

    @Override
    public void save(Advertisement advertisement) {
        advertisementRepository.save(advertisement);
    }

    @Override
    public void delete(Long id) {
        advertisementRepository.delete(id);
    }

    @Override
    public Iterable<Advertisement> findBySpecializationsIn(Collection<Specialization> specializations) {
        return advertisementRepository.findBySpecializationsIn(specializations);
    }
}

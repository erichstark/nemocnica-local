package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Advertisement;
import sk.stuba.fei.team.local.domain.Specialization;

import java.util.Collection;

public interface AdvertisementService {
    Advertisement findOne(Long id);

    Iterable<Advertisement> findAll();

    boolean exists(Long id);

    void save(Advertisement advertisement);

    void delete(Long id);

    Iterable<Advertisement> findBySpecializationsIn(Collection<Specialization> specializations);
}

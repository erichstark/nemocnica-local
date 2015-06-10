package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Specialization;

import java.util.List;

public interface SpecializationService {
    Specialization findOne(Long id);

    List<Specialization> findByName(String name);

    Iterable<Specialization> findAll();

    boolean exists(Long id);

    void update();
}

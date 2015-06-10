package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.Insurance;

import java.util.List;

public interface InsuranceService {

    Insurance findOne(Long id);

    List<Insurance> findByName(String name);

    Iterable<Insurance> findAll();

    boolean exists(Long id);

    void update();
}

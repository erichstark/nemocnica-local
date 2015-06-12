package sk.stuba.fei.team.local.service;

import sk.stuba.fei.team.local.domain.OpeningHours;

import java.util.List;

public interface OpeningHoursService {

    List<OpeningHours> findByOfficeId(Long id);

    void save(OpeningHours openingHours);
}

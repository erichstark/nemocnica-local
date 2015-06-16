package sk.stuba.fei.team.local.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.team.local.api.RestConsumer;
import sk.stuba.fei.team.local.api.domain.OpeningHoursWrapper;
import sk.stuba.fei.team.local.domain.OpeningHours;
import sk.stuba.fei.team.local.repository.OpeningHoursRepository;

import java.util.List;

@Component("hoursService")
@Transactional
public class OpeningHoursServiceImpl implements OpeningHoursService {

    @Autowired
    private OpeningHoursRepository openingHoursRepository;

    @Autowired
    private RestConsumer restConsumer;

    @Override
    public List<OpeningHours> findByOfficeId(Long id) {
        return openingHoursRepository.findByOfficeId(id);
    }

    @Override
    public OpeningHours findOne(Long id) {
        return openingHoursRepository.findOne(id);
    }

    @Override
    public void save(OpeningHours openingHours) {
        Long id = (Long) restConsumer.post("hours", new OpeningHoursWrapper(openingHours), Long.class);
        openingHours.setId(id);
        openingHoursRepository.save(openingHours);
    }
}

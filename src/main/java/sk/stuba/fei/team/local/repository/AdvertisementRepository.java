package sk.stuba.fei.team.local.repository;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.team.local.domain.Advertisement;
import sk.stuba.fei.team.local.domain.Specialization;

import java.util.Collection;
import java.util.List;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
    List<Advertisement> findBySpecializationsIn(Collection<Specialization> specializations);
}

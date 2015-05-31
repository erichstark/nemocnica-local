package sk.stuba.fei.team.local.repository;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.team.local.domain.Rpi_Office;

public interface RpiOfficeRepository extends CrudRepository<Rpi_Office, String> {
    Rpi_Office findById(String id);
}

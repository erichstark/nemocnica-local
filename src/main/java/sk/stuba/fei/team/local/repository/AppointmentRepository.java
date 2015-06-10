package sk.stuba.fei.team.local.repository;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.team.local.domain.Appointment;
import sk.stuba.fei.team.local.domain.Office;
import sk.stuba.fei.team.local.domain.Patient;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findByDateAndOffice(@Temporal(TemporalType.DATE) Date date, Office office);

    Iterable<Appointment> findByPatient(Patient patient);


}

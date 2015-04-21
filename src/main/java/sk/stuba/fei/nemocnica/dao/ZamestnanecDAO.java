package sk.stuba.fei.nemocnica.dao;

import sk.stuba.fei.nemocnica.model.Zamestnanec;

import java.util.List;

/**
 * Created by matus_000 on 7.4.2015.
 */
public interface ZamestnanecDAO {
    List<Zamestnanec> findAll();

    Zamestnanec findById(int id);

    Zamestnanec findByUsername(String username);

    List<Zamestnanec> findDoctors(String fullName,String specialization, String town);

    void createOrUpdate(Zamestnanec zamestnanec);
}

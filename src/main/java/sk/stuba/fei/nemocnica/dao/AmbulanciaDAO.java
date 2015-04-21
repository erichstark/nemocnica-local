package sk.stuba.fei.nemocnica.dao;

import sk.stuba.fei.nemocnica.model.Ambulancia;

import java.util.List;

/**
 * Created by jakubrehak on 19/04/15.
 */
public interface AmbulanciaDAO {


        List<Ambulancia> findAll(String meno);

        Ambulancia findById(int id);

        Ambulancia findByMeno(String name);

        Ambulancia findByZariadenie(String zariadenie);

        void createOrUpdate(Ambulancia ambulancia);

}

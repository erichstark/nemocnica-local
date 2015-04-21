package sk.stuba.fei.nemocnica.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.nemocnica.model.Ambulancia;

import java.util.List;

/**
 * Created by jakubrehak on 19/04/15.
 */
public class AmbulanciaDAOImpl implements AmbulanciaDAO {

    private SessionFactory sessionFactory;

    public AmbulanciaDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Ambulancia> findAll(String meno) {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("Ambulancia.findAll").setParameter("meno",meno).list();
    }

    @Override
    @Transactional
    public Ambulancia findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Ambulancia) session.getNamedQuery("Ambulancia.findById").setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional
    public Ambulancia findByMeno(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (Ambulancia) session.getNamedQuery("Ambulancia.findByMeno").setParameter("name", name).uniqueResult();
    }

    @Override
    @Transactional
    public Ambulancia findByZariadenie(String zariadenie) {
        Session session = sessionFactory.getCurrentSession();
        return (Ambulancia) session.getNamedQuery("Ambulancia.findByZariadenie").setParameter("zariadenie", zariadenie).uniqueResult();
    }

    @Override
    @Transactional
    public void createOrUpdate(Ambulancia ambulancia) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(ambulancia);
    }
}

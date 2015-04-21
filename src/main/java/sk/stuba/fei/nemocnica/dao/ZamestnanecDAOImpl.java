package sk.stuba.fei.nemocnica.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import sk.stuba.fei.nemocnica.model.Zamestnanec;

import java.util.List;

/**
 * Created by matus_000 on 7.4.2015.
 */
public class ZamestnanecDAOImpl implements ZamestnanecDAO {

    private SessionFactory sessionFactory;

    public ZamestnanecDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Zamestnanec> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("Zamestnanec.findAll").list();
    }

    @Override
    @Transactional
    public Zamestnanec findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Zamestnanec) session.getNamedQuery("Zamestnanec.findById").setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional
    public Zamestnanec findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return (Zamestnanec) session.getNamedQuery("Zamestnanec.findByUsername").setParameter("username", username).uniqueResult();
    }

    @Override
    @Transactional
    public List<Zamestnanec> findDoctors(String fullName,String specialization, String town) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT zam FROM Zamestnanec zam ").list();
    }


    @Override
    @Transactional
    public void createOrUpdate(Zamestnanec zamestnanec) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(zamestnanec);
    }
}

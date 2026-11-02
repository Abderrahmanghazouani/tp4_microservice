package dao;

import entities.Machine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class MachineDao implements IDao<Machine> {

    @Override
    public boolean create(Machine o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Machine o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Machine o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Machine getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Machine.class, id);
        }
    }

    @Override
    public List<Machine> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Machine", Machine.class).list();
        }
    }

    /** Étape 10 : requête HQL - recherche des machines par marque. */
    public List<Machine> findByMarque(String marque) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Machine> query = session.createQuery(
                    "from Machine m where m.marque = :marque", Machine.class);
            query.setParameter("marque", marque);
            return query.list();
        }
    }

    /** HQL avec jointure : machines d'une salle donnée (par code). */
    public List<Machine> findBySalleCode(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Machine> query = session.createQuery(
                    "select m from Machine m join m.salle s where s.code = :code", Machine.class);
            query.setParameter("code", code);
            return query.list();
        }
    }
}

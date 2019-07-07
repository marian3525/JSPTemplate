package repository;

import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/*
    To save an entity first populate its parent reference and add the children to the parent
 */
public class DBRepo {
    private SessionFactory factory;

    public DBRepo() {
        factory = HibernateConfig.getSessionFactory();
    }

    /**
     * Save a entity in the database
     * @param entity Object to be saved
     */
    public void save(BaseEntity entity) {

        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(entity);

            session.getTransaction().commit();
            session.close();
        }
        catch(HibernateException he) {
            he.printStackTrace();
        }
    }

    public List<A> getAs() {
        try (Session session = factory.openSession()) {
            // from A: case sensitive, class name
            return session.createQuery("from A", A.class).getResultList();
        }
    }

    public List<B> getBs() {
        try (Session session = factory.openSession()) {
            // from A: case sensitive, class name
            return session.createQuery("from B", B.class).getResultList();
        }
    }

    public List<Company> getCompanies() {
        try (Session session = factory.openSession()) {
            // from A: case sensitive, class name
            return session.createQuery("from Company", Company.class).getResultList();
        }
    }

    public List<SiteUser> getUsers() {
        try(Session session = factory.openSession()) {
            List<SiteUser> all = session.createQuery("from SiteUser", SiteUser.class).getResultList();
            return all;
        }
    }

    public void deleteUser(int eid) {
        try(Session session = factory.openSession()) {

            Transaction trn = session.beginTransaction();

            SiteUser user = session.get(SiteUser.class, eid);

            session.delete(user);

            trn.commit();
            session.close();
        }
    }

    public void updateUser(int id, String username, String password) {
        try(Session session = factory.openSession()) {
            Transaction trn = session.beginTransaction();

            SiteUser user = session.get(SiteUser.class, id);
            user.setPassword(password);
            user.setUsername(username);


            session.update(user);

            trn.commit();
            session.close();
        }
    }
}
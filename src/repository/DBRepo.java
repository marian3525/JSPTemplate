package repository;

import model.A;
import model.B;
import model.BaseEntity;
import model.Company;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
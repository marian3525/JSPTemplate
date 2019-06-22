package repository;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

class HibernateConfig {

    static SessionFactory getSessionFactory() {

        Properties prop= new Properties();

        prop.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/hiber");

        // looks like a bug, Environment.DIALECT = "hibernate.dialect" doesn't work
        prop.setProperty("dialect", "org.hibernate.dialect.PostgresSQL");

        prop.setProperty(Environment.USER, "postgres");
        prop.setProperty(Environment.PASS, "postgres");
        prop.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        prop.setProperty(Environment.SHOW_SQL, "true");
        prop.setProperty(Environment.HBM2DDL_AUTO, "create-drop");

        /*SessionFactory sessionFactory = new Configuration().addProperties(prop).buildSessionFactory();
        Session session = sessionFactory.openSession();
         */
        Configuration config = new Configuration();
        config.setProperties(prop);
        config.addAnnotatedClass(A.class);
        config.addAnnotatedClass(B.class);
        config.addAnnotatedClass(Company.class);
        config.addAnnotatedClass(Person.class);
        config.addAnnotatedClass(Task.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        SessionFactory factory = config.buildSessionFactory(reg);

        return factory;
    }
}



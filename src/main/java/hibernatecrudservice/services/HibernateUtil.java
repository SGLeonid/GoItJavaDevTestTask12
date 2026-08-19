package hibernatecrudservice.services;

import hibernatecrudservice.data.Client;
import hibernatecrudservice.data.Planet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE = new HibernateUtil();
    private final SessionFactory sessionFactory;

    public static synchronized HibernateUtil getInstance() {
        return INSTANCE;
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void close() {
        sessionFactory.close();
    }

    private HibernateUtil() {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }
}

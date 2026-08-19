package hibernatecrudservice.services;

import hibernatecrudservice.DatabaseException;
import hibernatecrudservice.data.Planet;
import jakarta.persistence.RollbackException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlanetCrudService implements ICrudService<Planet, String> {
    @Override
    public String create(Planet planet) throws DatabaseException {
        validate(planet);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
            return planet.getId();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        } catch (RollbackException e) {
            throw new DatabaseException("Transaction commit error: " + e.getMessage(), e);
        }
    }

    @Override
    public Planet read(String id) throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.find(Planet.class, id);
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        }
    }

    public Planet findByName(String name) throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Query<Planet> query = session.createQuery("from planet where name = :name", Planet.class);
            query.setParameter("name", name);
            return query.getSingleResultOrNull();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        }
    }

    public List<Planet> findAll() throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from planet", Planet.class).list();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Planet planet) throws DatabaseException {
        validate(planet);
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        } catch (RollbackException e) {
            throw new DatabaseException("Transaction commit error: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Planet planet) throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        } catch (RollbackException e) {
            throw new DatabaseException("Transaction commit error: " + e.getMessage(), e);
        }
    }

    private void validate(Planet planet) {
        if (planet.getName().isEmpty() || planet.getName().length() > 500) {
            throw new IllegalArgumentException("Planet name length must be in range [1...500]");
        }
    }
}
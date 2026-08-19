package hibernatecrudservice.services;

import hibernatecrudservice.DatabaseException;
import hibernatecrudservice.data.Client;
import jakarta.persistence.RollbackException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientCrudService implements ICrudService<Client, Long> {
    @Override
    public Long create(Client client) throws DatabaseException {
        validate(client);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            return client.getId();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        } catch (RollbackException e) {
            throw new DatabaseException("Transaction commit error: " + e.getMessage(), e);
        }
    }

    @Override
    public Client read(Long id) throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.find(Client.class, id);
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        }
    }

    public Client findByName(String name) throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Query<Client> query = session.createQuery("from client where name = :name", Client.class);
            query.setParameter("name", name);
            return query.getResultStream().findFirst().orElse(null);
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        }
    }

    public List<Client> findAll() throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from client", Client.class).list();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Client client) throws DatabaseException {
        validate(client);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        } catch (RollbackException e) {
            throw new DatabaseException("Transaction commit error: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Client client) throws DatabaseException {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DatabaseException("Hibernate error: " + e.getMessage(), e);
        } catch (RollbackException e) {
            throw new DatabaseException("Transaction commit error: " + e.getMessage(), e);
        }
    }

    private static void validate(Client client) {
        if (client.getName().length() < 3 || client.getName().length() > 200) {
            throw new IllegalArgumentException("Client name length must be in range [3...200]");
        }
    }
}

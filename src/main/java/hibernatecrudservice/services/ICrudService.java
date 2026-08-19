package hibernatecrudservice.services;

import hibernatecrudservice.DatabaseException;

public interface ICrudService<T, K> {
    K create(T obj) throws DatabaseException;
    T read(K id) throws DatabaseException;
    void update(T obj) throws DatabaseException;
    void delete(T obj) throws DatabaseException;
}

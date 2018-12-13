package dao;

interface Dao<T> {
    Long create(T entity) throws DaoException;
    T read(Long id) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(Long id) throws DaoException;
}

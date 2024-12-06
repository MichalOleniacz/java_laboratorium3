package org.michaloleniacz.lab.dao;

public interface GenericDAO<T> {
    /*
     * Save given entity to persistent storage
     */
    void save(T entity);

    /*
     * Find an instance of given type in persistent storage querying by entity ID.
     */
    <K extends T> K findById(Long id);

    /*
     * Remove an entity from persistent storage
     */
    void delete(T entity);
}

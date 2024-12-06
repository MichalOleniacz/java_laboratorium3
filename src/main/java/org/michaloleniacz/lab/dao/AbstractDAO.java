package org.michaloleniacz.lab.dao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.michaloleniacz.lab.utils.HibernateUtil;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public abstract class AbstractDAO<T> implements GenericDAO<T> {
    private final Class<T> concreteEntityClass;

    protected AbstractDAO(Class<T> concreteEntityClass) {
        this.concreteEntityClass = concreteEntityClass;
    }

    @Override
    public void save(final T entity) {
        try (Session session = HibernateUtil.getSession()){
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (RuntimeException exception) {
            log.error("Failed to process database query\n" + exception);
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public void delete(final T entity) {
        try (Session session = HibernateUtil.getSession()){
            Transaction tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        } catch (RuntimeException exception) {
            log.error("Failed to process database query\n" + exception);
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public T findById(final Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(concreteEntityClass, id);
        } catch (RuntimeException exception) {
            log.error("Failed to retrieve value\n" + exception);
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}

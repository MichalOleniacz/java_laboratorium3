package org.michaloleniacz.lab.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.Nullable;
import org.michaloleniacz.lab.utils.HibernateUtil;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public abstract class AbstractDAO<T> implements GenericDAO<T> {
    private final Class<T> concreteEntityClass;

    protected AbstractDAO(Class<T> concreteEntityClass) {
        this.concreteEntityClass = concreteEntityClass;
    }

    private void execWriteTransactional(Consumer<Session> queryOperation) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            queryOperation.accept(session);
            transaction.commit();
        } catch (RuntimeException exception) {
            log.error("Failed to process database transactional query\n" + exception);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private @Nullable T execRead(Function<Session, T> queryOperation) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            return queryOperation.apply(session);
        } catch (RuntimeException exception) {
            log.error("Failed to process database read query\n" + exception);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public void save(final T entity) {
        execWriteTransactional(session -> session.persist(entity));
    }

    @Override
    public void delete(final T entity) {
        execWriteTransactional(session -> session.remove(entity));
    }

    @Override
    public T findById(final Long id) {
        return execRead(session -> session.get(concreteEntityClass, id));
    }
}

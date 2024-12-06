package org.michaloleniacz.lab.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.Nullable;
import org.michaloleniacz.lab.utils.HibernateUtil;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public abstract class AbstractDAO<T> implements GenericDAO<T> {
    private final Class<T> concreteEntityClass;

    protected AbstractDAO(Class<T> concreteEntityClass) {
        this.concreteEntityClass = concreteEntityClass;
    }

    private void execWriteTransactional(final Consumer<Session> queryOperation) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            queryOperation.accept(session);
            transaction.commit();
        } catch (RuntimeException exception) {
            log.error("Failed to process database transactional query\n" + exception);
            if (!Objects.isNull(transaction)) {
                transaction.rollback();
            }
        } finally {
            if (!Objects.isNull(session)) {
                session.close();
            }
        }
    }

    private @Nullable T execRead(final Function<Session, T> queryOperation) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            return queryOperation.apply(session);
        } catch (RuntimeException exception) {
            log.error("Failed to process database read query\n" + exception);
        } finally {
            if (!Objects.isNull(session)) {
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
    public <K extends T> @Nullable K findById(final Long id) {
        final T result = execRead(session -> session.get(concreteEntityClass, id));
        if (Objects.isNull(result)) return null;
        else return (K) result;
    }
}

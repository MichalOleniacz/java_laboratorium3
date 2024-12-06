package org.michaloleniacz.lab.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.Nullable;
import org.michaloleniacz.lab.utils.HibernateUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public abstract class AbstractDAO<T> implements GenericDAO<T> {
    private final Class<? extends T> concreteEntityClass;

    protected AbstractDAO(final Class<? extends T> entityClass) {
        this.concreteEntityClass = entityClass;
    }

    private static void execWriteTransactional(final Consumer<? super Session> queryOperation) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            queryOperation.accept(session);
            transaction.commit();
        } catch (final RuntimeException exception) {
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

    private @Nullable T execRead(final Function<? super Session, ? extends T> queryOperation) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            return queryOperation.apply(session);
        } catch (final RuntimeException exception) {
            log.error("Failed to process database read query\n" + exception);
        } finally {
            if (!Objects.isNull(session)) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public final void save(final T entity) {
        log.debug("Atempting to save entity to persistent storage");
        AbstractDAO.execWriteTransactional(session -> session.persist(entity));
    }

    @Override
    public final void delete(final T entity) {
        log.debug("Atempting to remove entity from persistent storage");
        AbstractDAO.execWriteTransactional(session -> session.remove(entity));
    }

    @Override
    public <K extends T> @Nullable K findById(final Long id) {
        log.debug("Atempting to get entity from persistent storage");
        final T result = execRead(session -> session.get(concreteEntityClass, id));
        return Optional.ofNullable(result).map(it -> (K) it).orElse(null);
    }
}

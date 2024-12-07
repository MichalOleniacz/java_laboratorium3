package org.michaloleniacz.lab.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.michaloleniacz.lab.LabMainApplication;

import java.util.Objects;

/**
 * Utility object for working with Hibernate abstracting configuration and session logic.
 */
@Slf4j
public enum HibernateUtil {
    ;

    private static final String HIBERNATE_CFG_XML_FALLBACK = LabMainApplication.HIBERNATE_CFG_PATH;
    private static SessionFactory sessionFactory;

    /**
     * Dynamically configure hibernate and build {@link SessionFactory} from a config located at given path
     * @param hibernateConfigPath file path for configuration used in {@link Configuration}
     */
    public static void initialize(final String hibernateConfigPath) {
        try {
            sessionFactory = new Configuration().configure(hibernateConfigPath).buildSessionFactory();
            log.info("Hibernate initialized from " + hibernateConfigPath);
        } catch (HibernateException exception) {
            log.error("Failed to create Hibernate SessionFactory:" + exception);
            throw new ExceptionInInitializerError(exception);
        }
    }

    /**
     * Get new {@link Session} instance from available {@link SessionFactory}.
     * If {@link SessionFactory} is not defined, auto-initialize it using fallback config path.
     * @return new {@link Session} instance
     */
    public static Session getSession() {
        if (Objects.isNull(sessionFactory) || sessionFactory.isClosed()) {
            initialize(HIBERNATE_CFG_XML_FALLBACK);
            return getSession();
        }
        log.debug("Opening Hibernate session.");
        return sessionFactory.openSession();
    }

    /**
     * Close current {@link SessionFactory}
     */
    public static void closeSession() {
        if (Objects.isNull(sessionFactory)) {
            return;
        }
        log.debug("Closing Hibernate session.");
        sessionFactory.close();
    }
}

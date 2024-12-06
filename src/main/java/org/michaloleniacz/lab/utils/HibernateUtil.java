package org.michaloleniacz.lab.utils;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.michaloleniacz.lab.Main;

import java.util.Objects;

@Slf4j
public enum HibernateUtil {
    ;

    private static final String HIBERNATE_CFG_XML_FALLBACK = Main.HIBERNATE_CFG_PATH;
    private static SessionFactory sessionFactory;

    public static void initialize(final String hibernateConfigPath) {
        try {
            sessionFactory = new Configuration().configure(hibernateConfigPath).buildSessionFactory();
            log.info("Hibernate initialized from " + hibernateConfigPath);
        } catch (HibernateException exception) {
            log.error("Failed to create Hibernate SessionFactory:" + exception);
            throw new ExceptionInInitializerError(exception);
        }
    }

    public static Session getSession() {
        if (Objects.isNull(sessionFactory) || sessionFactory.isClosed()) {
            initialize(HIBERNATE_CFG_XML_FALLBACK);
            return getSession();
        }
        log.debug("Opening Hibernate session.");
        return sessionFactory.openSession();
    }

    public static void closeSession() {
        if (Objects.isNull(sessionFactory)) {
            return;
        }
        log.debug("Closing Hibernate session.");
        sessionFactory.close();
    }
}

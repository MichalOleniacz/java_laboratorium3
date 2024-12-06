package org.michaloleniacz.lab.utils;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public enum HibernateUtil {
    ;

    private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    private static void createSessionFactory() {
        try {
            sessionFactory = new Configuration().configure(HIBERNATE_CFG_XML).buildSessionFactory();
            log.info("Hibernate initialized from " + HIBERNATE_CFG_XML);
        } catch (HibernateException exception) {
            log.error("Failed to create Hibernate SessionFactory:" + exception);
            throw new ExceptionInInitializerError(exception);
        }
    }

    public static Session getSession() {
        if(sessionFactory == null || sessionFactory.isClosed()) {
            createSessionFactory();
            return getSession();
        }
        log.debug("Opening Hibernate session.");
        return sessionFactory.openSession();
    }

    public static void closeSession() {
        if(sessionFactory == null) {
            return;
        }
        log.debug("Closing Hibernate session.");
        sessionFactory.close();
    }
}

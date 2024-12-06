package org.michaloleniacz.lab.util;

import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HibernateUtilTest {

    @Test
    @DisplayName("HibernateUtil should throw when initialized with invalid path")
    public void initializeWithInvalidPath() {
        final String invalidPath = "foo";
        assertThatExceptionOfType(ExceptionInInitializerError.class)
                .isThrownBy(() -> HibernateUtil.initialize(invalidPath));
    }

    @Test
    @DisplayName("HibernateUtil should autoinitialize using fallback path when getting instance")
    public void getSessionAutoInitialize() {
        HibernateUtil.closeSession();

        Session session = HibernateUtil.getSession();

        assertThat(session).isInstanceOf(Session.class);
    }
}

package org.michaloleniacz.lab.dao;

import org.junit.jupiter.api.*;
import org.michaloleniacz.lab.enums.Color;
import org.michaloleniacz.lab.model.Rectangle;
import org.michaloleniacz.lab.model.Shape;
import org.michaloleniacz.lab.model.Triangle;
import org.michaloleniacz.lab.utils.HibernateUtil;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShapeDAOTest {

    ShapeDAO shapeDao = null;
    Rectangle testRect = null;
    Triangle testTrig = null;

    @BeforeAll
    public void setUp() {
        HibernateUtil.initialize("hibernate-test.cfg.xml");
    }

    @BeforeEach
    public void beforeEach() {
        shapeDao = new ShapeDAO();
        testRect = new Rectangle(1, 2, Color.RED);
        testTrig = new Triangle(1, 2, 3, Color.BLUE);
    }

    @AfterAll
    public void cleanup() {
        HibernateUtil.closeSession();
    }

    @Test
    @DisplayName("ShapeDAO should save and retrieve new Rectangle object")
    public void testRectangleSaveAndRetrieve() {
        shapeDao.save(testRect);

        Rectangle retrieved = shapeDao.findById(testRect.getId());

        assertThat(retrieved).isNotNull();
        assertThat(retrieved).isInstanceOf(Shape.class);
        assertThat(retrieved.getId()).isEqualTo(testRect.getId());
        assertThat(Rectangle.isRectangle(retrieved)).isTrue();
    }

    @Test
    @DisplayName("ShapeDAO should save and retrieve new Triangle object")
    public void testTriangleSaveAndRetrieve() {
        shapeDao.save(testTrig);

        Triangle retrieved = shapeDao.findById(testTrig.getId());

        assertThat(retrieved).isNotNull();
        assertThat(retrieved).isInstanceOf(Shape.class);
        assertThat(retrieved.getId()).isEqualTo(testTrig.getId());
        assertThat(Triangle.isTriangle(retrieved)).isTrue();
    }
}

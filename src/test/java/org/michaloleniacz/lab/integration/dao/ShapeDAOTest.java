package org.michaloleniacz.lab.integration.dao;

import org.junit.jupiter.api.*;
import org.michaloleniacz.lab.domain.model.Rectangle;
import org.michaloleniacz.lab.domain.model.Shape;
import org.michaloleniacz.lab.domain.model.Triangle;
import org.michaloleniacz.lab.domain.record.Color;
import org.michaloleniacz.lab.util.HibernateUtil;

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

    @Test
    @DisplayName("ShapeDAO should save and then delete new Rectangle object")
    public void testRectangleSaveAndDelete() {
        shapeDao.save(testRect);

        shapeDao.delete(testRect);
        Rectangle retrieved = shapeDao.findById(testRect.getId());

        assertThat(retrieved).isNull();
    }

    @Test
    @DisplayName("ShapeDAO should save and then delete new Triangle object")
    public void testTriangleSaveAndDelete() {
        shapeDao.save(testTrig);

        shapeDao.delete(testTrig);
        Triangle retrieved = shapeDao.findById(testTrig.getId());

        assertThat(retrieved).isNull();
    }

    @Test
    @DisplayName("ShapeDAO should return null when not found")
    public void testFindNullReturn() {
        Triangle retrievedTrig = shapeDao.findById(testTrig.getId());
        Triangle retrievedRect = shapeDao.findById(testRect.getId());

        assertThat(retrievedTrig).isNull();
        assertThat(retrievedRect).isNull();
    }
}

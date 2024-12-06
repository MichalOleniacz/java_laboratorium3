package org.michaloleniacz.lab;

import org.michaloleniacz.lab.dao.ShapeDAO;
import org.michaloleniacz.lab.enums.Color;
import org.michaloleniacz.lab.model.Rectangle;
import org.michaloleniacz.lab.model.Shape;
import org.michaloleniacz.lab.model.Triangle;
import org.michaloleniacz.lab.utils.HibernateUtil;
import org.michaloleniacz.lab.utils.ShapeDescriber;

/**
 * Application entry point
 */
@SuppressWarnings({"UseOfSystemOutOrSystemErr", "PublicMethodWithoutLogging"})
public class Main {

    /**
     * Application entry point
     * @param args arg list
     */
    public static void main(final String[] args) {
        HibernateUtil.initialize("hibernate.cfg.xml");
        Rectangle rect = new Rectangle(1,2, Color.GREEN);
        Triangle triangle = new Triangle(4,5,6, Color.BLUE);

        ShapeDAO shapeDAO = new ShapeDAO();

        for(int i = 1; i <= 100; i++) {
            Shape shape;
            if (i % 2 == 0) shape = new Rectangle(i, i, Color.RED);
            else shape = new Triangle(i, i, i, Color.BLUE);
            shapeDAO.save(shape);
            ShapeDescriber.describe(shapeDAO.findById(shape.getId()));
            shapeDAO.delete(shape);
        }
    }
}

package org.michaloleniacz.lab;

import org.michaloleniacz.lab.dao.ShapeDAO;
import org.michaloleniacz.lab.record.Color;
import org.michaloleniacz.lab.model.Rectangle;
import org.michaloleniacz.lab.model.Shape;
import org.michaloleniacz.lab.model.Triangle;
import org.michaloleniacz.lab.util.HibernateUtil;
import org.michaloleniacz.lab.util.ShapeDescriber;

/**
 * Application entry point
 */
@SuppressWarnings({"PublicMethodWithoutLogging"})
public class Main {

    public static final String HIBERNATE_CFG_PATH = "hibernate.cfg.xml";

    /**
     * Application entry point
     * @param args arg list
     */
    public static void main(final String[] args) {
        HibernateUtil.initialize(HIBERNATE_CFG_PATH);

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

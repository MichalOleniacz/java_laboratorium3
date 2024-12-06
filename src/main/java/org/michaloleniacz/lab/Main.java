package org.michaloleniacz.lab;

import org.michaloleniacz.lab.dao.ShapeDAO;
import org.michaloleniacz.lab.enums.Color;
import org.michaloleniacz.lab.model.Rectangle;
import org.michaloleniacz.lab.model.Shape;
import org.michaloleniacz.lab.model.Triangle;
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
        Rectangle rect = new Rectangle(1,2, Color.GREEN);
        Triangle triangle = new Triangle(4,5,6, Color.BLUE);

        ShapeDAO shapeDAO = new ShapeDAO();

        shapeDAO.save(rect);
        shapeDAO.save(rect);
        shapeDAO.save(triangle);
        Shape trig = shapeDAO.findById(triangle.id);

        ShapeDescriber.describe(trig);
    }
}

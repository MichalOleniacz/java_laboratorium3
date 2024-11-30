package org.michaloleniacz.lab;

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
        final Color color = new Color((byte) 1, (byte) 2, (byte) 3);

        final Shape[] concreteShapes = {
                new Rectangle(1,2, Color.GREEN),
                new Triangle(4,5,6, Color.BLUE)
        };

        ShapeDescriber.describeMany(concreteShapes);
    }
}

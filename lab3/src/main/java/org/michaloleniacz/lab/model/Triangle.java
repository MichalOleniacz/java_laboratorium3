package org.michaloleniacz.lab.model;

import lombok.ToString;
import org.michaloleniacz.lab.enums.Color;

/**
 * Concrete implementation of a {@link Shape} representing a triangle
 */
@ToString(callSuper = true)
public class Triangle extends Shape {
    private final double x;
    private final double y;
    private final double z;

    public Triangle(final double x, final double y, final double z, final Color color) {
        super(color);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Internal method for computing the perimeter
     * @param halfPerimeter
     * @return
     */
    private double computePerimeter(final double halfPerimeter) {
        return Math.sqrt(
                halfPerimeter
                        * (halfPerimeter - x)
                        * (halfPerimeter - y)
                        * (halfPerimeter - z)
        );
    }

    /**
     * Calculates the area
     * @return {@link Double}  rectangle area
     */
    @Override
    public final double getArea() {
        final double halfPerimeter = getPerimeter() / 2;
        return computePerimeter(halfPerimeter);
    }

    /**
     * Calculates the perimeter
     * @return {@link Double} rectangle perimeter
     */
    @Override
    public final double getPerimeter() {
        return x + y + z;
    }
}

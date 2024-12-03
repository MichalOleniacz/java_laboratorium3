package org.michaloleniacz.lab.model;

import lombok.ToString;
import org.michaloleniacz.lab.enums.Color;

/**
 * Concrete implementation of a {@link Shape} representing a rectangle
 */
@ToString(callSuper = true)
public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(final double width, final double height, final Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the perimeter
     * @return {@link Double} rectangle perimeter
     */
    @Override
    public final double getPerimeter() {
        return 2.0f * (width + height);
    }

    /**
     * Calculates the area
     * @return {@link Double}  rectangle area
     */
    @Override
    public final double getArea() {
        return width * height;
    }
}

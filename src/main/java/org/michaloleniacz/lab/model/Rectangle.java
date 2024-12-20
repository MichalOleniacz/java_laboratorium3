package org.michaloleniacz.lab.model;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.michaloleniacz.lab.enums.Color;

/**
 * Concrete implementation of a {@link Shape} representing a rectangle
 */
@ToString(callSuper = true)
@Slf4j
public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(final double width, final double height, final Color color) {
        super(color);
        if (width <= 0 || height <= 0) {
            String msg = "Invalid dimensions.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
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

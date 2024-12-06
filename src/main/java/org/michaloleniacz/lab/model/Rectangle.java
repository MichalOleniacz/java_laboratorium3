package org.michaloleniacz.lab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.michaloleniacz.lab.record.Color;

/**
 * Concrete implementation of a {@link Shape} representing a rectangle
 */
@Slf4j
@Entity
@Table(name = "rectangles")
@ToString(callSuper = true)
@NoArgsConstructor
public class Rectangle extends Shape {
    @Column(nullable = false)
    private double width;
    @Column(nullable = false)
    private double height;

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
     * Check if given {@link Shape} is a {@link Rectangle}
     * @param shape
     * @return bool
     */
    public static boolean isRectangle(final Shape shape) {
        return shape instanceof Rectangle;
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

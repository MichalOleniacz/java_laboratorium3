package org.michaloleniacz.lab.model;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.michaloleniacz.lab.enums.Color;

/**
 * Abstract class representing any shape.
 */
@ToString
@RequiredArgsConstructor
public abstract class Shape {
    private final Color color;
    /**
     * Debug method outputting the class name to stdout
     */
    public void print() {
        System.out.println(this.getClass().getSimpleName());
    }

    /**
     * Calculates the shapes area
     */
    protected abstract double getArea();

    /**
     * Calculates the shapes perimeter
     */
    protected abstract double getPerimeter();

    /**
     * @return {@link Color} as a string
     */
    public final String getColorDescription() {
        return color.toString();
    }
}

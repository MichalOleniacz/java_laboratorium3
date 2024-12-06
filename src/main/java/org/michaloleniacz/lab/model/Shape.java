package org.michaloleniacz.lab.model;

import jakarta.persistence.*;
import lombok.ToString;
import org.michaloleniacz.lab.enums.Color;

/**
 * Abstract class representing any shape.
 */
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "shapes")
public abstract class Shape {
    @Embedded
    private Color color;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Shape() {
    }

    public Shape(final Color color) {
        this.color = color;
    }

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

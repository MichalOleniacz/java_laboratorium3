package org.michaloleniacz.lab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.michaloleniacz.lab.enums.Color;

/**
 * Concrete implementation of a {@link Shape} representing a triangle
 */
@Entity
@Table(name = "triangles")
@Slf4j
@ToString(callSuper = true)
@NoArgsConstructor
public class Triangle extends Shape {
    @Column
    private double x;
    @Column
    private double y;
    @Column
    private double z;

    public Triangle(final double x, final double y, final double z, final Color color) {
        super(color);
        if (x <= 0 || y <= 0 || z <= 0) {
            String msg = "Invalid dimensions.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
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

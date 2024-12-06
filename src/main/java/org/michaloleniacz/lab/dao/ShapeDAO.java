package org.michaloleniacz.lab.dao;

import org.michaloleniacz.lab.model.Shape;

/**
 * Data Access Object for {@link Shape} objects
 */
public class ShapeDAO extends AbstractDAO<Shape> {
    public ShapeDAO() {
        super(Shape.class);
    }
}

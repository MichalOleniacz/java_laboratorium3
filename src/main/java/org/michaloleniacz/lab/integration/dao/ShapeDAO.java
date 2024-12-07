package org.michaloleniacz.lab.integration.dao;

import org.michaloleniacz.lab.domain.model.Shape;

/**
 * Data Access Object for {@link Shape} objects
 */
public class ShapeDAO extends AbstractDAO<Shape> {
    public ShapeDAO() {
        super(Shape.class);
    }
}

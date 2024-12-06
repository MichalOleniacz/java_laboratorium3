package org.michaloleniacz.lab.utils;

import lombok.extern.slf4j.Slf4j;
import org.michaloleniacz.lab.model.Shape;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Utility static singleton for displaying information about {@link Shape} to stdout
 */
@Slf4j
public enum ShapeDescriber {
    ;

    /**
     * Print most important information about {@link Shape} to stdout
     * @param concreteShape One or many instances of {@link Shape}
     */
    public static final void describe(final Shape concreteShape) {
        log.info(concreteShape.toString());
    }

    /**
     * Print most important information about {@link Shape} to stdout
     * @param concreteShapes One or many instances of {@link Shape}
     */
    public static final void describeMany(final Shape[] concreteShapes) {
        Arrays.stream(concreteShapes).forEach(it -> log.info(it.toString()));
    }

    public static final void describeMany(final List<Shape> concreteShapes) {
        concreteShapes.forEach(it -> log.info(it.toString()));
    }
}

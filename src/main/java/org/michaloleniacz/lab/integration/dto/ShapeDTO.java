package org.michaloleniacz.lab.integration.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.michaloleniacz.lab.domain.model.Rectangle;
import org.michaloleniacz.lab.domain.model.Shape;
import org.michaloleniacz.lab.domain.model.Triangle;
import org.michaloleniacz.lab.domain.record.Color;

@Data
public class ShapeDTO {
    @NotNull
    private final String dtype;
    @NotNull
    @Positive
    private final double width;
    @NotNull
    @Positive
    private final double height;
    @NotNull
    @Positive
    private final double x;
    @NotNull
    @Positive
    private final double y;
    @NotNull
    @Positive
    private final double z;

    public final Shape toModel() {
        return switch (dtype) {
            case "rectangle" -> new Rectangle(width, height, Color.RED);
            case "triangle" -> new Triangle(x, y, z, Color.RED);
            default -> throw new IllegalStateException("Unexpected value: " + dtype);
        };
    }
}

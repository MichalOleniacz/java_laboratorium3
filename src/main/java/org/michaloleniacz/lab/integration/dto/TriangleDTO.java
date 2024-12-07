package org.michaloleniacz.lab.integration.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.michaloleniacz.lab.domain.model.Triangle;
import org.michaloleniacz.lab.domain.record.Color;

@Data
public class TriangleDTO implements Modelable<Triangle> {
    @NotNull
    @Positive
    private final double x;
    @NotNull
    @Positive
    private final double y;
    @NotNull
    @Positive
    private final double z;
    @NotNull
    private final Color color;

    @Override
    public Triangle toModel() {
        return new Triangle(x, y, z, color);
    }
}

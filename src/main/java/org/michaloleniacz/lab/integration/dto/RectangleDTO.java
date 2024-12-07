package org.michaloleniacz.lab.integration.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.michaloleniacz.lab.domain.model.Rectangle;
import org.michaloleniacz.lab.domain.record.Color;


@Data
public class RectangleDTO implements Modelable<Rectangle> {
    @NotNull
    @Positive
    private final double width;
    @NotNull
    @Positive
    private final double height;
    @NotNull
    private final Color color;

    @Override
    public Rectangle toModel() {
        return new Rectangle(width, height, color);
    }
}

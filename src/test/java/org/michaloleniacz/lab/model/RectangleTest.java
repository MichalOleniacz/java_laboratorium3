package org.michaloleniacz.lab.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.michaloleniacz.lab.record.Color;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RectangleTest {
    static Rectangle rectangle;

    @BeforeEach
    public void setupRectangle() {
        rectangle = new Rectangle(1, 2, Color.RED);
    }

    @Test
    @DisplayName("Constructor call with invalid dimentions should throw")
    public void invalidArgsConstructorCall() {
        assertThatThrownBy(() -> new Rectangle(0, 0, Color.RED))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Get area should return correct shape area")
    public void getAreaTest() {
        // given
        final double areaExpected = 2;
        // when
        final double areaActual = rectangle.getArea();
        // then
        assertThat(areaActual).isEqualTo(areaExpected);
    }


    @Test
    @DisplayName("Get parameters should return correct shape parameters")
    public void getParameterTest() {
        // given
        final double peremiterExpected = 6;

        // when
        final double peremiterActual = rectangle.getPerimeter();
        // then
        assertThat(peremiterActual).isEqualTo(peremiterExpected);
    }

    @Test
    @DisplayName("Get correct sould return color description")
    public void getColorDescription() {
        // given
        final String expectedDescription = Color.RED.toString();

        // when
        final String descriptionActual = rectangle.getColorDescription();
        // then
        assertThat(descriptionActual).isEqualTo(expectedDescription);
    }
}

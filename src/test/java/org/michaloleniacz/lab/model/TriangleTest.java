package org.michaloleniacz.lab.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.michaloleniacz.lab.enums.Color;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TriangleTest {
    static Triangle uut;

    @BeforeEach
    public void setupTriangle() {
        uut = new Triangle(3, 4, 5, Color.RED);
    }

    @Test
    @DisplayName("Constructor call with invalid dimentions should throw")
    public void invalidArgsConstructorCall() {
        assertThatThrownBy(() -> new Triangle(0, 0, 0, Color.RED))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Get area should return correct shape area")
    public void getAreaTest() {
        // given
        final double areaExpected = 6;
        // when
        final double areaActual = uut.getArea();
        // then
        assertThat(areaActual).isEqualTo(areaExpected);
    }

    @Test
    @DisplayName("Get parameters should return correct shape parameters")
    public void getParameterTest() {
        // given
        final double peremiterExpected = 12;

        // when
        final double peremiterActual = uut.getPerimeter();
        // then
        assertThat(peremiterActual).isEqualTo(peremiterExpected);
    }

    @Test
    @DisplayName("Get correct should return color description")
    public void getColorDescription() {
        // given
        final Triangle uut = new Triangle(1, 2, 3, Color.RED);
        final String expectedDescription = Color.RED.toString();

        // when
        final String descriptionActual = uut.getColorDescription();
        // then
        assertThat(descriptionActual).isEqualTo(expectedDescription);
    }
}

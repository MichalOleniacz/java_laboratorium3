package org.michaloleniacz.lab.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorTest {
    @Test
    @DisplayName("Constructor call without explicit opacity sets default opacity value")
    public void createsColorWithDefaultOpacity() {
        // given
        final byte expectedOpacity = (byte) 0;
        // when
        final Color colorActual = new Color((byte) 1, (byte) 1, (byte) 1);
        // then
        assertThat(colorActual.opacity()).isEqualTo(expectedOpacity);
    }
}

package org.michaloleniacz.lab.domain.record;

import jakarta.persistence.Embeddable;

/**
 * Representation of an RGBA color
 * @param red red channel [0-255]
 * @param green green channel [0-255]
 * @param blue blue channel [0-255]
 * @param opacity transparency value [0-255]
 */
@SuppressWarnings("ClassWithoutLogger")
@Embeddable
public record Color(byte red, byte green, byte blue, byte opacity) {
    private static final byte RGB_RANGE_MIN = (byte) 0;
    private static final byte RGB_RANGE_MAX = (byte) 255;
    /**
     * Instance of Color with default values representing red (255,0,0)
     */
    public static final Color RED = new Color(
            RGB_RANGE_MAX,
            RGB_RANGE_MIN,
            RGB_RANGE_MIN,
            RGB_RANGE_MAX
    );
    /**
     * Instance of Color with default values representing pure green (0,255,0)
     */
    public static final Color GREEN = new Color(
            RGB_RANGE_MIN,
            RGB_RANGE_MAX,
            RGB_RANGE_MIN,
            RGB_RANGE_MAX
    );
    /**
     * Instance of Color with default values representing pure blue (0,0,255)
     */
    public static final Color BLUE = new Color(
            RGB_RANGE_MIN,
            RGB_RANGE_MIN,
            RGB_RANGE_MAX,
            RGB_RANGE_MAX
    );

    /**
     * {@link Color}
     */
    public Color(final byte red, final byte green, final byte blue) {
        this(red, green, blue, RGB_RANGE_MIN);
    }

    @Override
    public String toString() {
        return String.format(
                "RGBA(%d, %d, %d, %d)",
                Byte.toUnsignedInt(red),
                Byte.toUnsignedInt(green),
                Byte.toUnsignedInt(blue),
                Byte.toUnsignedInt(opacity)
        );
    }
}

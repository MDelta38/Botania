/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.integration.coloredlights;

public class ColoredLightHelper {
    public static int[] r = new int[]{0, 15, 0, 8, 0, 10, 0, 10, 5, 15, 8, 15, 0, 15, 15, 15};
    public static int[] g = new int[]{0, 0, 15, 3, 0, 0, 15, 10, 5, 10, 15, 15, 8, 0, 12, 15};
    public static int[] b = new int[]{0, 0, 0, 0, 15, 15, 15, 10, 5, 13, 0, 0, 15, 15, 10, 15};
    private static int[][] packedColors = null;

    public static int makeRGBLightValue(float r, float g, float b, float currentLightValue) {
        if (r < 0.0f) {
            r = 0.0f;
        } else if (r > 1.0f) {
            r = 1.0f;
        }
        if (g < 0.0f) {
            g = 0.0f;
        } else if (g > 1.0f) {
            g = 1.0f;
        }
        if (b < 0.0f) {
            b = 0.0f;
        } else if (b > 1.0f) {
            b = 1.0f;
        }
        int brightness = (int)(currentLightValue * 15.0f);
        return (brightness &= 0xF) | ((int)(15.0f * b) << 15) + ((int)(15.0f * g) << 10) + ((int)(15.0f * r) << 5);
    }

    public static int getPackedColor(int meta, int light) {
        if (packedColors == null) {
            ColoredLightHelper.initPackedColors();
        }
        return packedColors[meta][light];
    }

    private static void initPackedColors() {
        packedColors = new int[16][16];
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                ColoredLightHelper.packedColors[i][j] = ColoredLightHelper.makeRGBLightValue(r[15 - i], g[15 - i], b[15 - i], (float)j / 16.0f);
            }
        }
    }
}


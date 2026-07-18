/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.integration.coloredlights;

import vazkii.botania.common.Botania;

public class LightHelper {
    public static int makeRGBLightValue(float r, float g, float b, float currentLightValue) {
        return Botania.lightHelper.makeRGBLightValue(r, g, b, (int)currentLightValue);
    }

    public static int getPackedColor(int meta, int light) {
        return Botania.lightHelper.getPackedColor(meta, light);
    }
}

